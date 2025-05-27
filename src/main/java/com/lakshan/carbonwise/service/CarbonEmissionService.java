package com.lakshan.carbonwise.service;

import com.lakshan.carbonwise.config.CarbonInterfaceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CarbonEmissionService {

    private final CarbonInterfaceConfig carbonInterfaceConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public CarbonEmissionService(CarbonInterfaceConfig carbonInterfaceConfig, RestTemplateBuilder restTemplateBuilder) {
        this.carbonInterfaceConfig = carbonInterfaceConfig;
        this.restTemplate = restTemplateBuilder.build();
    }

    public String calculateCarbonEmissions(String body) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + carbonInterfaceConfig.getApiKey());
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://www.carboninterface.com/api/v1/emissions_calculations",
                HttpMethod.POST,
                entity,
                String.class
        );
        return response.getBody();
    }
}