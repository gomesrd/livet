package br.com.livet.domain.model.security;

import br.com.livet.infrastructure.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SecurityUtils {

    public static UsernamePasswordAuthenticationToken createAuthenticationToken(User user, Object credentials) {
        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + user.getRole())
        );
        return new UsernamePasswordAuthenticationToken(user, credentials, authorities);
    }
}
