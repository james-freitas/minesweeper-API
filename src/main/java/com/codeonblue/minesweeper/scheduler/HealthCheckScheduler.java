package com.codeonblue.minesweeper.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class HealthCheckScheduler {

    Logger log = LoggerFactory.getLogger(HealthCheckScheduler.class);

    @Value("#{environment.APPLICATION_BASE_URL}")
    private String baseUrl;

    /*
        fixedRate is the time in milliseconds between calls
        20 minutes is 1_200_000
    */
    @Scheduled(fixedRate = 5_000)
    public void scheduleHealthCheck() {

        final String url = baseUrl + "/actuator/health";

        log.info("Hitting on application health check.");

        try {
            final RestTemplate restTemplate = new RestTemplate();
            final String response = restTemplate.getForObject(url, String.class);
            log.info("Health check success hit: " + response);
        } catch (RestClientResponseException e) {
            log.error("Health check failed: " + e.getResponseBodyAsString());
        }
    }
}
