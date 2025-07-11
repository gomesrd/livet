package br.com.livet.domain.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class HealthCheckSelfService {

    private final RestTemplate restTemplate = new RestTemplate();
    private String healthUrl;

    @PostConstruct
    public void init() {
        // Descobre a URL completa da aplicação
        String port = System.getenv().getOrDefault("PORT", "8080");
        this.healthUrl = "http://localhost:" + port + "/actuator/health";
    }

    @Scheduled(fixedDelay = 30000)
    public void checkOwnHealth() {
        try {
            String response = restTemplate.getForObject(healthUrl, String.class);
            log.info("Health check OK: {}", response);
        } catch (Exception e) {
            log.error("Erro ao consultar o health check da própria API", e);
        }
    }
}
