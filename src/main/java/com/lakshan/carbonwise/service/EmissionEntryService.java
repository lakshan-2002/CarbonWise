package com.lakshan.carbonwise.service;

import com.lakshan.carbonwise.entity.EmissionEntry;
import com.lakshan.carbonwise.model.CarbonRequestMapper;
import com.lakshan.carbonwise.repository.EmissionEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmissionEntryService {

    private final EmissionEntryRepository emissionEntryRepository;
    private final CarbonEmissionService carbonEmissionService;
    private final CarbonRequestMapper carbonRequestMapper;
    private final CarbonCalculationService carbonCalculationService;

    @Autowired
    public EmissionEntryService(EmissionEntryRepository emissionEntryRepository,
                                CarbonEmissionService carbonEmissionService,
                                CarbonRequestMapper carbonRequestMapper, CarbonCalculationService carbonCalculationService
    ) {
        this.emissionEntryRepository = emissionEntryRepository;
        this.carbonEmissionService = carbonEmissionService;
        this.carbonRequestMapper = carbonRequestMapper;
        this.carbonCalculationService = carbonCalculationService;
    }

    public void addNewEmissionEntry(EmissionEntry emissionEntry) {
        if(emissionEntry.getType().equals("Energy"))
            carbonCalculationService.calculateEnergyEmissions(emissionEntry);
        else if (emissionEntry.getType().equals("Transportation")) {

        }
        emissionEntryRepository.save(emissionEntry);
    }

    public List<EmissionEntry> getAllEmissionEntries() {
        return emissionEntryRepository.findAll();
    }

    public EmissionEntry getEmissionEntryById(int id) {
        return emissionEntryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("No emission entry found with id: " + id));
    }

    public void updateEmissionEntry(EmissionEntry emissionEntry) throws RuntimeException {
        if (emissionEntryRepository.existsById(emissionEntry.getId())) {
            if(emissionEntry.getType().equals("Energy"))
                carbonCalculationService.calculateEnergyEmissions(emissionEntry);
            else if (emissionEntry.getType().equals("Transportation")) {

            }
            emissionEntryRepository.save(emissionEntry);
        } else
            throw new RuntimeException("No emission entry found with id: " + emissionEntry.getId());
    }

    public void deleteEmissionEntry(int id) {
        if (emissionEntryRepository.existsById(id))
            emissionEntryRepository.deleteById(id);
        else
            throw new RuntimeException("No emission entry found with id: " + id);
    }
}
