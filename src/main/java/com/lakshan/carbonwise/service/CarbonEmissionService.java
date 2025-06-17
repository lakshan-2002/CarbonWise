package com.lakshan.carbonwise.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lakshan.carbonwise.config.CarbonSutraConfig;
import com.lakshan.carbonwise.model.CarbonBusinessAirTravelRequest;
import com.lakshan.carbonwise.model.CarbonElectricityRequest;
import com.lakshan.carbonwise.model.CarbonFuelEstimateRequest;
import com.lakshan.carbonwise.model.CarbonVehicleEstimateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CarbonEmissionService {

    private final CarbonSutraConfig carbonSutraConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public CarbonEmissionService(CarbonSutraConfig carbonSutraConfig, RestTemplateBuilder restTemplateBuilder) {
        this.carbonSutraConfig = carbonSutraConfig;
        this.restTemplate = restTemplateBuilder.build();

    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + carbonSutraConfig.getBearerAPIKey());
        headers.set("X-RapidAPI-Key", carbonSutraConfig.getRapidAPIKey());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private double fetchCarbonSutraEmission(String apiUrl, String requestBody) {
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, getHttpHeaders());

        try {
            String response = restTemplate.postForObject(
                    apiUrl,
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

    public double calculateElectricityEmissions(CarbonElectricityRequest carbonElectricityRequest) {
        String electricityApiUrl = "https://carbonsutra1.p.rapidapi.com/electricity_estimate";

        String electricityRequestBody = carbonElectricityRequest.buildElectricityAPIRequest();
        return fetchCarbonSutraEmission(electricityApiUrl, electricityRequestBody);

    }

    public double calculateVehicleEstimateEmissions(CarbonVehicleEstimateRequest vehicleEstimateRequest) {
        String vehicleEstimateApiUrl = "https://carbonsutra1.p.rapidapi.com/vehicle_estimate_by_type";

        String vehicleEstimateRequestBody = vehicleEstimateRequest.buildVehicleEstimateAPIRequest();
        return fetchCarbonSutraEmission(vehicleEstimateApiUrl, vehicleEstimateRequestBody);
    }

    public double calculateFuelEstimateEmissions(CarbonFuelEstimateRequest fuelEstimateRequest) {
        String fuelEstimateApiUrl = "https://carbonsutra1.p.rapidapi.com/fuel_estimate";

        String fuelEstimateRequestBody = fuelEstimateRequest.buildFuelEstimateRequest();
        return fetchCarbonSutraEmission(fuelEstimateApiUrl, fuelEstimateRequestBody);
    }

    public double calculateBusinessAirTravelEmissions(CarbonBusinessAirTravelRequest businessAirTravelRequest) {
        String businessAirTravelApiUrl = "https://carbonsutra1.p.rapidapi.com/flight_estimate";

        String flightRequestBody = businessAirTravelRequest.buildBusinessAirTravelRequest();
        return fetchCarbonSutraEmission(businessAirTravelApiUrl, flightRequestBody);
    }


}
