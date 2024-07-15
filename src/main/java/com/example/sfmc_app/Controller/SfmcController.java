package com.example.sfmc_app.Controller;

import com.example.sfmc_app.Model.Automation.AutomationItem;
import com.example.sfmc_app.Model.Automation.AutomationSend;
import com.example.sfmc_app.Model.DataExtention.DataExtension;
import com.example.sfmc_app.Model.DataExtention.DataExtensionItem;
import com.example.sfmc_app.Model.DataExtention.DataExtensionResponse;
import com.example.sfmc_app.Model.Journey.JourneyItem;
import com.example.sfmc_app.Service.SfmcApiClientService;
import com.example.sfmc_app.Service.SfmcAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

@RestController
@RequestMapping("/api/sfmc")
@CrossOrigin(origins = "http://localhost:4200")
public class SfmcController {

    @Autowired
   private SfmcApiClientService apiClientService;
    @Autowired
    private SfmcAuthService authService;

    @GetMapping("/automations")
    public ResponseEntity<List<AutomationItem>> getAutomations() {
        return ResponseEntity.ok(apiClientService.getAutomations());
    }

    // In SfmcController.java

    @PostMapping("/automations")
    public ResponseEntity<Void> createAutomation(@RequestBody(required = false) String rawBody) {
        System.out.println("Received raw body: " + rawBody);
        if (rawBody == null || rawBody.trim().isEmpty() || "true".equals(rawBody.trim())) {
            return ResponseEntity.badRequest().build();
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            AutomationSend automationSend = objectMapper.readValue(rawBody, AutomationSend.class);
            apiClientService.createAutomation(automationSend);
            return ResponseEntity.status(HttpStatus.CREATED).build();
            // Rest of your method...
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/automations/{id}")
    public ResponseEntity<Void> deleteAutomation(@PathVariable String id) {
        System.out.println("id is : " + id);
        try {
            apiClientService.deleteAutomation(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/journeys")
    public List<JourneyItem> getJourneys() {
        return apiClientService.getJourneys();
    }

    @GetMapping("/journeys/{id}")
    public JourneyItem getJourneyDetails(@PathVariable String id) {
        return apiClientService.getJourneyDetails(id);
    }

    @GetMapping("/data-extensions")
    public List<DataExtension> getDataExtensions() {
        return apiClientService.getDataExtensions();
    }

    @GetMapping("/data-extensions/rows")
    public List<DataExtensionItem> getDataExtensionRows(@RequestParam String key) {
        return apiClientService.getDataExtensionRows(key);
    }
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken() {
        try {
            String newAccessToken = authService.refreshAccessToken();
            return ResponseEntity.ok(newAccessToken);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unable to refresh token");
        }
    }
}
