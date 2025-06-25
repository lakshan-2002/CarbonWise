package com.lakshan.carbonwise.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class AiRecommendationDTO {

    @JsonProperty("title")
    private String title;

    @JsonProperty("category")
    private String category;

    @JsonProperty("carbon_impact_tCO2e_per_year")
    private double carbonImpact;

    @JsonProperty("financial_impact_dollars_per_year")
    private double financialImpact;

    @JsonProperty("implementation_cost_dollars")
    private double implementationCost;

    @JsonProperty("payback_period_years")
    private String paybackPeriod;

    @JsonProperty("implementation_difficulty")
    private String implementationDifficulty;


    public List<AiRecommendationDTO> parseRecommendations(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonResponse, new TypeReference<List<AiRecommendationDTO>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse recommendations" + e.getMessage());
        }

    }
}
