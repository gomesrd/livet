package br.com.livet.domain.model.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AuthLoginResponse {
    private String token;
    private String refreshToken;
    private String expiresIn;
}
