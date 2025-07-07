package br.com.livet.infrastructure.configuration;

import br.com.livet.domain.model.security.SecurityUtils;
import br.com.livet.domain.service.user.UserService;
import br.com.livet.infrastructure.entity.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import jakarta.servlet.ServletException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Configuration
public class FirebaseFilter extends OncePerRequestFilter {

    private static final Logger logger = LogManager.getLogger(FirebaseFilter.class);

    private final FirebaseAuth firebaseAuth;
    private final UserService userService;
    private final SecurityUtils securityUtils;

    public FirebaseFilter(FirebaseAuth firebaseAuth, UserService userService, SecurityUtils securityUtils) {
        this.firebaseAuth = firebaseAuth;
        this.userService = userService;
        this.securityUtils = securityUtils;
    }


    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws IOException, ServletException {
        try {
            SecurityContextHolder.getContext().setAuthentication(null);
            String authorization = getTokenFromRequest(request);

            if (authorization != null && !authorization.trim().isEmpty()) {
                FirebaseToken token = getFirebaseToken(authorization);

                if (token != null) {
                    UserRecord user = firebaseAuth.getUser(token.getUid());

                    if (!user.isDisabled()) { // TODO: verificar e-mail se necessário
                        User userPrincipal = getUser(user);

                        if (userPrincipal != null) {
                            UsernamePasswordAuthenticationToken authentication = SecurityUtils.createAuthenticationToken(userPrincipal, token);

                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("Erro durante autenticação Firebase: ", ex);
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    protected User getUser(UserRecord user) {
        return userService.findByEmail(user.getEmail());
    }

    protected FirebaseToken getFirebaseToken(String authorization) {
        try {
            return firebaseAuth.verifyIdToken(authorization, true);
        } catch (Exception e) {
            logger.warn("Token Firebase inválido: " + e.getMessage());
            return null;
        }
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.replace("Bearer", "").trim();
        }
        return null;
    }
}
