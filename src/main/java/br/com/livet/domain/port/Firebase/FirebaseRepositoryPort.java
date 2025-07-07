package br.com.livet.domain.port.Firebase;

import br.com.livet.domain.model.auth.AuthLoginRequest;
import br.com.livet.domain.model.auth.AuthLoginResponse;
import br.com.livet.domain.model.user.CreateUserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface FirebaseRepositoryPort {
    AuthLoginResponse login(AuthLoginRequest authLoginRequest) throws JsonProcessingException;
    void refreshToken();
    UserRecord createUser(CreateUserRequest user);
    Optional<UserRecord> getUserByEmail(String email);

}
