package com.lakshan.carbonwise.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lakshan.carbonwise.entity.EmissionEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarbonElectricityRequest {

    private String type;

    @JsonProperty("electricity_unit")
    private String electricityUnit;

    @JsonProperty("electricity_value")
    private double electricityValue;

    private String country;

    public String buildElectricityAPIRequest() {
        return String.format(
                """
                {
                    "type": "%s",
                    "electricity_unit": "%s",
                    "electricity_value": "%s",
                    "country_name": "%s"
                }
                """.formatted(type, electricityUnit, electricityValue, country)
        );
    }

}
