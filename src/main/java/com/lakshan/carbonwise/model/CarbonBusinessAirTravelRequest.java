package com.lakshan.carbonwise.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarbonBusinessAirTravelRequest {

    @JsonProperty("iata_airport_from")
    private String iataAirportFrom;

    @JsonProperty("iata_airport_to")
    private String iataAirportTo;

    @JsonProperty("number_of_passengers")
    private int numberOfPassengers;

    public String buildBusinessAirTravelRequest() {
        return String.format(
                """
               {
                   "iata_airport_from": "%s",
                   "iata_airport_to": "%s",
                   "number_of_passengers": "%s"
               }
               """.formatted(iataAirportFrom, iataAirportTo, numberOfPassengers)
       );
   }
}
