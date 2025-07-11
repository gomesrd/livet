package br.com.livet.infrastructure.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import com.google.firebase.auth.FirebaseAuth;
import java.io.InputStream;

@Configuration
public class FirebaseConfiguration {

    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            // Caminho absoluto do arquivo secreto no Render
            String secretPath = "/etc/secrets/firebase-service-account.json";
            try (InputStream serviceAccount = new FileInputStream(secretPath)) {
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                FirebaseApp.initializeApp(options);
            }
        }

        return FirebaseAuth.getInstance();
    }
}
