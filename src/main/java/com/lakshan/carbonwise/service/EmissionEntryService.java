package com.lakshan.carbonwise.service;

import com.lakshan.carbonwise.entity.EmissionEntry;
import com.lakshan.carbonwise.repository.EmissionEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmissionEntryService {

    private final EmissionEntryRepository emissionEntryRepository;
    private final CarbonCalculationService carbonCalculationService;
    private final RecommendationService recommendationService;

    @Autowired
    public EmissionEntryService(EmissionEntryRepository emissionEntryRepository,
                                CarbonCalculationService carbonCalculationService,
                                RecommendationService recommendationService

    ) {
        this.emissionEntryRepository = emissionEntryRepository;
        this.carbonCalculationService = carbonCalculationService;
        this.recommendationService = recommendationService;
    }

    public void addNewEmissionEntry(EmissionEntry emissionEntry) {
        double co2Emission = carbonCalculationService.calculateEmission(emissionEntry);
        emissionEntry.setCo2Emission(co2Emission);
        emissionEntryRepository.save(emissionEntry);

        String prompt = """
                "Based on the type %s, data %s, and CO₂ emissions %s, suggest exactly 5 personalized, sustainable and practical actions to reduce these emissions.\\n" +
                "Respond with a JSON array of 5 objects. Each object must include the following keys:\\n" +
                     "- title (string)\\n" +
                     "- category (string)\\n" +
                     "- carbon_impact_tCO2e_per_year (number)\\n" +
                     "- financial_impact_dollars_per_year (number)\\n" +
                     "- implementation_cost_dollars (number)\\n" +
                     "- payback_period_years (number)\\n" +
                     "- implementation_difficulty (string: \\"Low\\", \\"Medium\\", or \\"High\\")\\n\\n" +
                "Only return the JSON array. Do not include any explanation, markdown, or additional text.",
                """.formatted(emissionEntry.getType(),
                emissionEntry.getData(),
                emissionEntry.getCo2Emission()
        );

        recommendationService.saveRecommendations(prompt, emissionEntry.getUser(), emissionEntry);

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
            double co2Emission = carbonCalculationService.calculateEmission(emissionEntry);
            emissionEntry.setCo2Emission(co2Emission);
            emissionEntryRepository.save(emissionEntry);

            String prompt = """
                    "Based on the type %s, data %s, and CO₂ emissions %s, suggest exactly 5 personalized, sustainable and practical actions to reduce these emissions.\\n" +
                    "Respond with a JSON array of 5 objects. Each object must include the following keys:\\n" +
                         "- title (string)\\n" +
                         "- category (string)\\n" +
                         "- carbon_impact_tCO2e_per_year (number)\\n" +
                         "- financial_impact_dollars_per_year (number)\\n" +
                         "- implementation_cost_dollars (number)\\n" +
                         "- payback_period_years (number)\\n" +
                         "- implementation_difficulty (string: \\"Low\\", \\"Medium\\", or \\"High\\")\\n\\n" +
                    "Only return the JSON array. Do not include any explanation, markdown, or additional text.",
                    """.formatted(emissionEntry.getType(),
                    emissionEntry.getData(),
                    emissionEntry.getCo2Emission()
            );
            recommendationService.saveRecommendations(prompt, emissionEntry.getUser(), emissionEntry);
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
