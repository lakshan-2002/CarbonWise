package com.lakshan.carbonwise.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lakshan.carbonwise.entity.EmissionEntry;
import com.lakshan.carbonwise.model.CarbonRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class CarbonCalculationService {

    private final CarbonRequestMapper carbonRequestMapper;
    private final CarbonEmissionService carbonEmissionService;
    private final EmissionCategory emissionCategory;

    @Autowired
    public CarbonCalculationService(CarbonRequestMapper carbonRequestMapper, CarbonEmissionService carbonEmissionService) throws IOException {
        this.carbonRequestMapper = carbonRequestMapper;
        this.carbonEmissionService = carbonEmissionService;

        ObjectMapper mapper = new ObjectMapper();
        File file = new ClassPathResource("emission-category-data.json").getFile();
        emissionCategory = mapper.readValue(file, EmissionCategory.class);
    }

    public double calculateEmission(EmissionEntry emissionEntry) throws RuntimeException {
        String key = emissionEntry.getData();
        if (!emissionEntry.getMethod().isEmpty())
            key += ";" + emissionEntry.getMethod();

        if (emissionCategory.calculated.containsKey(key))
            return emissionCategory.calculated.get(key) * emissionEntry.getAmount();
        else if (emissionCategory.endpoint.contains(key)) {
            return switch (key) {
                case "Electricity Usage" -> carbonEmissionService.calculateElectricityEmissions(
                        carbonRequestMapper.toElectricityRequest(emissionEntry)
                );
                case "Employee Commuting" -> carbonEmissionService.calculateVehicleEstimateEmissions(
                        carbonRequestMapper.toVehicleEstimateRequest(emissionEntry)
                );
                case "Fuel Usage" -> carbonEmissionService.calculateFuelEstimateEmissions(
                        carbonRequestMapper.toFuelEstimateRequest(emissionEntry)
                );
                case "Business Air Travel" -> carbonEmissionService.calculateBusinessAirTravelEmissions(
                        carbonRequestMapper.toBusinessAirTravelRequest(emissionEntry)
                );
                default -> throw new RuntimeException("Invalid emission category");
            };
        } else {
            throw new RuntimeException("Invalid emission category");
        }
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
        if (emissionEntry.getData().equals("Business Air Travel")) {
            var airTravelRequest = carbonRequestMapper.toBusinessAirTravelRequest(emissionEntry);
            double flightEmissions = carbonEmissionService.
                    calculateBusinessAirTravelEmissions(airTravelRequest);

            emissionEntry.setCo2Emission(flightEmissions);
        }


    }
}
