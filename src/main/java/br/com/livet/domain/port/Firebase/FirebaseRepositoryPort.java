package br.com.livet.domain.port.Firebase;

import br.com.livet.domain.model.auth.AuthLoginRequest;
import br.com.livet.domain.model.auth.AuthLoginResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public interface FirebaseRepositoryPort {
    AuthLoginResponse login(AuthLoginRequest authLoginRequest) throws JsonProcessingException;
    void refreshToken();
    void createUser();

}
