package com.lakshan.carbonwise.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarbonVehicleEstimateRequest {

    @JsonProperty("vehicle_type")
    private String vehicleType;

    @JsonProperty("distance_unit")
    private String distanceUnit;

    @JsonProperty("distance_value")
    private double distanceValue;


    public String buildVehicleEstimateAPIRequest() {
        return String.format(
                """
                {
                     "vehicle_type": "%s",
                     "distance_unit": "%s",
                     "distance_value": "%s"
                }
                """.formatted(vehicleType, distanceUnit, distanceValue)
        );
    }
}
