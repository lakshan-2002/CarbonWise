package com.lakshan.carbonwise.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lakshan.carbonwise.config.CarbonSutraConfig;
import com.lakshan.carbonwise.model.CarbonElectricityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CarbonEmissionService {

    private final CarbonSutraConfig carbonSutraConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public CarbonEmissionService(CarbonSutraConfig carbonSutraConfig, RestTemplateBuilder restTemplateBuilder) {
        this.carbonSutraConfig = carbonSutraConfig;
        this.restTemplate = restTemplateBuilder.build();

    }

    public double calculateEnergyEmissions(CarbonElectricityRequest carbonElectricityRequest) {
        String electricityApiUrl = "https://carbonsutra1.p.rapidapi.com/electricity_estimate";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + carbonSutraConfig.getBearerAPIKey());
        headers.set("X-RapidAPI-Key", carbonSutraConfig.getRapidAPIKey());
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = carbonElectricityRequest.buildElectricityAPIRequest();
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            String response = restTemplate.postForObject(
                    electricityApiUrl,
                    requestEntity,
                    String.class
            );

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            return rootNode.get("data").get("co2e_kg").asDouble();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
