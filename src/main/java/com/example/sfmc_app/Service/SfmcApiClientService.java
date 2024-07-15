package com.example.sfmc_app.Service;


import com.example.sfmc_app.Model.Automation.AutomationItem;
import com.example.sfmc_app.Model.Automation.AutomationResponse;
import com.example.sfmc_app.Model.Automation.AutomationSend;
import com.example.sfmc_app.Model.DataExtention.DataExtension;
import com.example.sfmc_app.Model.DataExtention.DataExtensionItem;
import com.example.sfmc_app.Model.DataExtention.DataExtensionResponse;
import com.example.sfmc_app.Model.DataExtention.DataExtensionRowResponse;
import com.example.sfmc_app.Model.Journey.JourneyItem;
import com.example.sfmc_app.Model.Journey.JourneyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileReader;
import java.util.*;

@Service
public class SfmcApiClientService {

    @Value("${sfmc.clientId}")
    private String clientId;

    @Value("${sfmc.clientSecret}")
    private String clientSecret;

    @Value("${sfmc.authUrl}")
    private String authUrl;

    @Value("${sfmc.baseUrl}")
    private String baseUrl;

    @Autowired
    private SfmcAuthService authService;

    private RestTemplate restTemplate = new RestTemplate();


    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authService.getAccessToken());
        return headers;
    }

    public List<AutomationItem> getAutomations() {
        String url = baseUrl + "/automation/v1/automations";
        ResponseEntity<AutomationResponse> response = getSfmcData(url, AutomationResponse.class);
        return response.getBody().getItems();
    }
// In SfmcApiClientService.java

    // In SfmcApiClientService.java

    public ResponseEntity<Void> createAutomation(AutomationSend automationSend) {
        String url = baseUrl + "/automation/v1/automations";
        HttpEntity<AutomationSend> entity = new HttpEntity<>(automationSend, createHeaders());
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, entity, Void.class);
        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("Response headers: " + response.getHeaders());

        return response;
    }

    public void deleteAutomation(String id) {
        String url = baseUrl + "/automation/v1/automations/" + id;
        HttpEntity<String> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to delete automation: " + response.getBody());
        }
    }



    public List<JourneyItem> getJourneys() {
        String url = baseUrl + "/interaction/v1/interactions";
        ResponseEntity<JourneyResponse> response = getSfmcData(url, JourneyResponse.class);
       response.getBody().getItems().stream().forEach(n -> n.setActivities(getJourneyDetails(n.getId()).getActivities()));
        return response.getBody().getItems();
    }

    public JourneyItem getJourneyDetails(String journeyId) {
        String url = baseUrl + "/interaction/v1/interactions/" + journeyId;
        ResponseEntity<JourneyItem> response = getSfmcData(url, JourneyItem.class);
        JourneyItem journey = response.getBody();

        // Fetch activities for the journey and set them as steps
        return journey;
    }
    public List<DataExtension> getDataExtensions() {
        String url = baseUrl + "/data/v1/customobjectdata";
        ResponseEntity<DataExtensionResponse> response = getSfmcData(url, DataExtensionResponse.class);
        return response.getBody().getItems();
    }

    public List<DataExtensionItem> getDataExtensionRows(String dataExtensionKey) {
        String url = baseUrl + "/data/v1/customobjectdata/key/" + dataExtensionKey + "/rowset";

        ResponseEntity<DataExtensionRowResponse> response = getSfmcData(url, DataExtensionRowResponse.class);
        return response.getBody().getItems();
    }

    private <T> ResponseEntity<T> getSfmcData(String url, Class<T> responseType) {
        HttpEntity<String> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<T> response = null;
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 401) {
                authService.refreshAccessToken();
                entity = new HttpEntity<>(createHeaders());
                response = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
            } else {
                throw e;
            }
        }
        return response;
    }


}
