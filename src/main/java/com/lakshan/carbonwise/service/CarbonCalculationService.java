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

    public void calculateEnergyEmissions(EmissionEntry emissionEntry){
        if (emissionEntry.getData().equals("Electricity Usage")) {
            var carbonElectricityRequest = carbonRequestMapper.toElectricityRequest(emissionEntry);
            double co2Emission = carbonEmissionService.
                    calculateElectricityEmissions(carbonElectricityRequest);

            emissionEntry.setCo2Emission(co2Emission);
        }
        if (emissionEntry.getData().equals("Natural Gas")) {
            double co2Emission = 53.06 * emissionEntry.getAmount();
            emissionEntry.setCo2Emission(co2Emission);
        }
    }

    public void calculateTransportEmissions(EmissionEntry emissionEntry){

    }
}
