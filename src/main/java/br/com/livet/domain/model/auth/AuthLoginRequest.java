package br.com.livet.domain.model.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AuthLoginRequest {
    private String email;
    private String password;
    private Boolean returnSecureToken = true;
}
