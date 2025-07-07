package br.com.livet.infrastructure.repository;

import br.com.livet.domain.model.auth.AuthLoginRequest;
import br.com.livet.domain.model.auth.AuthLoginResponse;
import br.com.livet.domain.port.Firebase.FirebaseRepositoryPort;
import br.com.livet.infrastructure.properties.FirebaseProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component

public class FirebaseRepository implements FirebaseRepositoryPort {
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;
    private final FirebaseProperties firebaseProperties;


    public FirebaseRepository(OkHttpClient okHttpClient, ObjectMapper objectMapper, FirebaseProperties firebaseProperties) {
        this.okHttpClient = okHttpClient;
        this.objectMapper = objectMapper;
        this.firebaseProperties = firebaseProperties;
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
                System.out.println(bodyTree.toString());
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
    public void createUser() {
        // Implement user creation logic here
    }
}
