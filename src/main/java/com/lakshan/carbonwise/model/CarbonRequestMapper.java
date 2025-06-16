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
                emissionEntry.getData(),
                emissionEntry.getUnit(),
                emissionEntry.getAmount()
        );
    }
    public CarbonFuelEstimateRequest toFuelEstimateRequest(EmissionEntry emissionEntry) {
        return new CarbonFuelEstimateRequest(
                emissionEntry.getType().toLowerCase(),
                emissionEntry.getData(),
                emissionEntry.getUnit(),
                emissionEntry.getAmount()
        );
    }

    public CarbonWasteRequest toWasteRequest(EmissionEntry emissionEntry, String wasteType) {
        return new CarbonWasteRequest(
                "waste",
                wasteType,
                emissionEntry.getUnit(),
                emissionEntry.getAmount()
        );
    }

    public CarbonOtherRequest toOtherRequest(EmissionEntry emissionEntry) {
        return new CarbonOtherRequest(
                "custom",
                emissionEntry.getData(),
                emissionEntry.getAmount(),
                emissionEntry.getUnit()
        );
    }
}
