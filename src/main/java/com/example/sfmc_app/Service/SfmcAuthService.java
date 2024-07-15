package com.example.sfmc_app.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SfmcAuthService {

    @Value("${sfmc.clientId}")
    private String clientId;

    @Value("${sfmc.clientSecret}")
    private String clientSecret;

    @Value("${sfmc.authUrl}")
    private String authUrl;

    private String accessToken;

    public synchronized void authenticate() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> body = new HashMap<>();
        body.put("client_id", clientId);
        body.put("client_secret", clientSecret);
        body.put("grant_type", "client_credentials");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(authUrl, request, Map.class);

        accessToken = (String) response.getBody().get("access_token");
    }

    public String refreshAccessToken() {
        authenticate();
        return accessToken;
    }


    public String getAccessToken() {
        if (accessToken == null) {
            authenticate();
        }
        return accessToken;
    }
}
