package com.lakshan.carbonwise.service;

import com.lakshan.carbonwise.entity.EmissionEntry;
import com.lakshan.carbonwise.model.CarbonRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarbonCalculationService {

    private final CarbonRequestMapper carbonRequestMapper;
    private final CarbonEmissionService carbonEmissionService;

    @Autowired
    public CarbonCalculationService(CarbonRequestMapper carbonRequestMapper, CarbonEmissionService carbonEmissionService) {
        this.carbonRequestMapper = carbonRequestMapper;
        this.carbonEmissionService = carbonEmissionService;
    }

    public void calculateEnergyEmissions(EmissionEntry emissionEntry) {
        if (emissionEntry.getData().equals("Electricity Usage")) {
            var carbonElectricityRequest = carbonRequestMapper.toElectricityRequest(emissionEntry);
            double electricityEmissions = carbonEmissionService.
                    calculateElectricityEmissions(carbonElectricityRequest);

            emissionEntry.setCo2Emission(electricityEmissions);
        }
        if (emissionEntry.getData().equals("Natural Gas")) {
            double gasEmissions = 53.06 * emissionEntry.getAmount();
            emissionEntry.setCo2Emission(gasEmissions);
        }
    }

    public void calculateTransportEmissions(EmissionEntry emissionEntry) {
        if (emissionEntry.getData().equals("Employee Commuting")) {
            var vehicleEstimateRequest = carbonRequestMapper.toVehicleEstimateRequest(emissionEntry);
            double vehicleEmissions = carbonEmissionService.
                    calculateVehicleEstimateEmissions(vehicleEstimateRequest);

            emissionEntry.setCo2Emission(vehicleEmissions);
        }
        if (emissionEntry.getData().equals("Fuel Usage")) {
            var fuelEstimateRequest = carbonRequestMapper.toFuelEstimateRequest(emissionEntry);
            double fuelEmissions = carbonEmissionService.
                    calculateFuelEstimateEmissions(fuelEstimateRequest);

            emissionEntry.setCo2Emission(fuelEmissions);
        }
        if (emissionEntry.getData().equals("Business Air Travel")){
            var airTravelRequest = carbonRequestMapper.toBusinessAirTravelRequest(emissionEntry);
            double flightEmissions = carbonEmissionService.
                    calculateBusinessAirTravelEmissions(airTravelRequest);

            emissionEntry.setCo2Emission(flightEmissions);
        }


    }
}
