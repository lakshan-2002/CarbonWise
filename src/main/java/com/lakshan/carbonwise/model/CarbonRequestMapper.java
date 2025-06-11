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

    public CarbonTransportationRequest toTransportRequest(EmissionEntry emissionEntry, String vehicleModelId) {
        return new CarbonTransportationRequest(
                "vehicle",
                emissionEntry.getUnit(),
                emissionEntry.getAmount(),
                vehicleModelId
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
