package br.com.livet.domain.service.auth;

import br.com.livet.domain.model.auth.AuthLoginRequest;
import br.com.livet.domain.model.auth.AuthLoginResponse;
import br.com.livet.domain.port.Firebase.FirebaseRepositoryPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final FirebaseRepositoryPort firebaseRepositoryPort;

    public AuthService(FirebaseRepositoryPort firebaseRepositoryPort) {
        this.firebaseRepositoryPort = firebaseRepositoryPort;
    }

    public AuthLoginResponse login(AuthLoginRequest authLoginRequest) throws JsonProcessingException {
        return firebaseRepositoryPort.login(authLoginRequest);
    }

    public String register(String username, String password) {
        // Implement registration logic here
        System.out.println("Registration attempt for user: " + username);
        return "Registration successful for user: " + username;
    }
}
