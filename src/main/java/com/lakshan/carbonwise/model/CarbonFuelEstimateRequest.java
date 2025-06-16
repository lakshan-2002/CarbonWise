package com.lakshan.carbonwise.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarbonFuelEstimateRequest {

    @JsonProperty("fuel_usage")
    private String fuelUsage;

    @JsonProperty("fuel_name")
    private String fuelName;

    @JsonProperty("fuel_unit")
    private String fuelUnit;

    @JsonProperty("fuel_value")
    private Double fuelValue;

    public String buildFuelEstimateRequest() {
        return String.format(
                """
                {
                    "fuel_usage": "%s",
                    "fuel_name": "%s",
                    "fuel_unit": "%s",
                    "fuel_value": "%s"
                }
                """.formatted(fuelUsage, fuelName, fuelUnit, fuelValue)
        );
    }

}
