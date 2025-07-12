package br.com.livet.infrastructure.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kotlin.jvm.Throws;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final ObjectMapper objectMapper;
    private final FirebaseFilter firebaseFilter;

    public SecurityConfiguration(ObjectMapper objectMapper, FirebaseFilter firebaseFilter) {
        this.objectMapper = objectMapper;
        this.firebaseFilter = firebaseFilter;
    }

    @Bean
    public AuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request,
                                 HttpServletResponse response,
                                 org.springframework.security.core.AuthenticationException authException) throws IOException {

                Map<String, Object> errorObject = new HashMap<>();
                int errorCode = HttpStatus.UNAUTHORIZED.value();

                errorObject.put("message", "Access Denied");
                errorObject.put("error", HttpStatus.UNAUTHORIZED);
                errorObject.put("code", errorCode);
                errorObject.put("timestamp", OffsetDateTime.now(ZoneOffset.UTC));

                response.setContentType("application/json;charset=UTF-8");
                response.setStatus(errorCode);
                response.getWriter().write(objectMapper.writeValueAsString(errorObject));
            }
        };
    }

    @Bean
    @Throws(exceptionClasses = Exception.class)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.NEVER)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/login",
                                "/auth/register",
                                "/health",
                                "/actuator/health",
                                "/home"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                        .anyRequest().authenticated()
                )

                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(
                        exceptionHandling -> exceptionHandling
                                .authenticationEntryPoint(restAuthenticationEntryPoint())
                );
        http.addFilterBefore(firebaseFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
