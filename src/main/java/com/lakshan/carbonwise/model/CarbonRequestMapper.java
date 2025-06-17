package com.lakshan.carbonwise.model;

import com.lakshan.carbonwise.entity.EmissionEntry;
import org.springframework.stereotype.Component;

@Component
public class CarbonRequestMapper {

    public CarbonElectricityRequest toElectricityRequest(EmissionEntry emissionEntry) {
        return new CarbonElectricityRequest(
                "estimate-electricity",
                emissionEntry.getUnit(),
                emissionEntry.getAmount(),
                emissionEntry.getUser().getBusiness().getCountry()
        );
    }

    public CarbonVehicleEstimateRequest toVehicleEstimateRequest(EmissionEntry emissionEntry) {
        return new CarbonVehicleEstimateRequest(
                emissionEntry.getMethod(),
                emissionEntry.getUnit(),
                emissionEntry.getAmount()
        );
    }
    public CarbonFuelEstimateRequest toFuelEstimateRequest(EmissionEntry emissionEntry) {
        return new CarbonFuelEstimateRequest(
                emissionEntry.getType().toLowerCase(),
                emissionEntry.getMethod(),
                emissionEntry.getUnit(),
                emissionEntry.getAmount()
        );
    }

    public CarbonBusinessAirTravelRequest toBusinessAirTravelRequest(EmissionEntry emissionEntry) {
        return new CarbonBusinessAirTravelRequest(
                emissionEntry.getIataAirportFrom(),
                emissionEntry.getIataAirportTo(),
                emissionEntry.getNumberOfPassengers()
        );
    }

}
