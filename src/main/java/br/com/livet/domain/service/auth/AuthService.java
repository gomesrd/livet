package br.com.livet.domain.service.auth;

import br.com.livet.domain.model.auth.AuthLoginRequest;
import br.com.livet.domain.model.auth.AuthLoginResponse;
import br.com.livet.domain.model.user.CreateUserRequest;
import br.com.livet.domain.port.Firebase.FirebaseRepositoryPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final FirebaseRepositoryPort firebaseRepositoryPort;

    public AuthService(FirebaseRepositoryPort firebaseRepositoryPort) {
        this.firebaseRepositoryPort = firebaseRepositoryPort;
    }

    public AuthLoginResponse login(AuthLoginRequest authLoginRequest) throws JsonProcessingException {
        return firebaseRepositoryPort.login(authLoginRequest);
    }

    public String createUser(CreateUserRequest user) {
        Optional<UserRecord> userFirebase = firebaseRepositoryPort.getUserByEmail(user.getEmail());

        if (userFirebase.isPresent()) {
            throw new RuntimeException("User already exists with email: " + user.getEmail());
        }
        return firebaseRepositoryPort.createUser(user).getUid();
    }
}
