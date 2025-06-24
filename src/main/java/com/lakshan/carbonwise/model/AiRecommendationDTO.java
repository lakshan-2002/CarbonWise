package com.lakshan.carbonwise.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lakshan.carbonwise.entity.Recommendation;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class AiRecommendationDTO {
    private String title;
    private String category;
    private double carbonImpact;
    private double financialImpact;
    private double implementationCost;
    private String paybackPeriod;
    private String implementationDifficulty;


    public List<AiRecommendationDTO> parseRecommendations(String jsonResponse) {
        try {
            Pattern pattern = Pattern.compile("```json\\s*\\{.*?\\}\\s*```", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(jsonResponse);

            if (matcher.find()) {
                String jsonBlock = matcher.group();
                String cleanedJson = jsonBlock.replaceAll("```json", "").replaceAll("```", "").trim();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(cleanedJson);

                JsonNode actions = root.path("actions");
                return objectMapper.readValue(actions.toString(), new TypeReference<List<AiRecommendationDTO>>() {});
            } else {
                throw new RuntimeException("Could not extract valid JSON block from response.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse recommendations: " + jsonResponse);
        }

    }
}
