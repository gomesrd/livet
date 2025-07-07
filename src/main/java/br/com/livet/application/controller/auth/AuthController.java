package br.com.livet.application.controller.auth;

import br.com.livet.domain.model.auth.AuthLoginRequest;
import br.com.livet.domain.model.auth.AuthLoginResponse;
import br.com.livet.domain.service.auth.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponse> login(
            @RequestBody AuthLoginRequest authLoginRequest
    ) throws JsonProcessingException {
        return status(OK).body(authService.login(authLoginRequest));
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        // Implement registration logic here
        return "Registration successful for user: " + username;
    }
}