package br.com.livet.infrastructure.repository.firebase;

import br.com.livet.domain.model.auth.AuthLoginRequest;
import br.com.livet.domain.model.auth.AuthLoginResponse;
import br.com.livet.domain.model.user.CreateUserRequest;
import br.com.livet.domain.port.firebase.FirebaseRepositoryPort;
import br.com.livet.infrastructure.properties.FirebaseProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component

public class FirebaseRepository implements FirebaseRepositoryPort {
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;
    private final FirebaseProperties firebaseProperties;
    private final FirebaseAuth firebaseAuth;


    public FirebaseRepository(OkHttpClient okHttpClient, ObjectMapper objectMapper, FirebaseProperties firebaseProperties, FirebaseAuth firebaseAuth) {
        this.okHttpClient = okHttpClient;
        this.objectMapper = objectMapper;
        this.firebaseProperties = firebaseProperties;
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    public AuthLoginResponse login(AuthLoginRequest authLoginRequest) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(authLoginRequest);

        RequestBody body = RequestBody.create(json, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(firebaseProperties.getAuthenticationUrl())
                .post(body)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            String responseBody = response.body() != null ? response.body().string() : null;

            if (response.isSuccessful()) {
                JsonNode bodyTree = objectMapper.readTree(responseBody);
                String idToken = bodyTree.hasNonNull("idToken") ? bodyTree.get("idToken").asText() : null;
                String refreshToken = bodyTree.hasNonNull("refreshToken") ? bodyTree.get("refreshToken").asText() : null;
                String expiresIn = bodyTree.hasNonNull("expiresIn") ? bodyTree.get("expiresIn").asText() : null;

                return AuthLoginResponse.builder()
                        .token(idToken)
                        .refreshToken(refreshToken)
                        .expiresIn(expiresIn)
                        .build();
            } else {
                String errorMessage = "";
                if (responseBody != null) {
                    JsonNode errorNode = objectMapper.readTree(responseBody)
                            .path("error")
                            .path("message");
                    errorMessage = errorNode.isTextual() ? errorNode.asText() : "Erro desconhecido.";
                }
                throw new BadRequestException(errorMessage);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void refreshToken() {
        // Implement token refresh logic here
    }

    @Override
    public UserRecord createUser(CreateUserRequest user) {
        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(user.getEmail())
                    .setPassword(user.getPassword())
                    .setDisplayName(user.getFirstName());

            return firebaseAuth.createUser(request);
        } catch (Exception e) {
            throw new RuntimeException("Error creating user in Firebase: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<UserRecord> getUserByEmail(String email) {
        try {
            return Optional.ofNullable(firebaseAuth.getUserByEmail(email));
        } catch (FirebaseAuthException e) {
            // logar ou tratar
            return Optional.empty();
        }
    }

}
