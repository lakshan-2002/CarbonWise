package com.lakshan.carbonwise.service;

import com.lakshan.carbonwise.entity.EmissionEntry;
import com.lakshan.carbonwise.entity.Recommendation;
import com.lakshan.carbonwise.entity.User;
import com.lakshan.carbonwise.model.AiRecommendationDTO;
import com.lakshan.carbonwise.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final AiRecommendationDTO aiRecommendationDTO;
    private final AiService aiService;

    @Autowired
    public RecommendationService(RecommendationRepository recommendationRepository, AiRecommendationDTO aiRecommendationDTO, AiService aiService) {
        this.recommendationRepository = recommendationRepository;
        this.aiRecommendationDTO = aiRecommendationDTO;
        this.aiService = aiService;
    }

    public void saveRecommendations(String recommendationPrompt,
                                    User user,
                                    EmissionEntry emissionEntry) {

        String chatResponse = aiService.chat(recommendationPrompt);
        List<AiRecommendationDTO> dataList = aiRecommendationDTO.parseRecommendations(chatResponse);

        for (var recommendationDTO : dataList) {
            addNewRecommendation(recommendationDTO, user, emissionEntry);
        }

    }

    public void addNewRecommendation(AiRecommendationDTO aiRecommendationDTO,
                                     User user,
                                     EmissionEntry emissionEntry) {

        Recommendation recommendation = new Recommendation();
        recommendation.setUser(user);
        recommendation.setEmissionEntry(emissionEntry);
        recommendation.setTitle(aiRecommendationDTO.getTitle());
        recommendation.setCategory(aiRecommendationDTO.getCategory());
        recommendation.setCarbonImpact(aiRecommendationDTO.getCarbonImpact());
        recommendation.setFinancialImpact(aiRecommendationDTO.getFinancialImpact());
        recommendation.setImplementationCost(aiRecommendationDTO.getImplementationCost());
        recommendation.setPaybackPeriod(aiRecommendationDTO.getPaybackPeriod());
        recommendation.setImplementationDifficulty(aiRecommendationDTO.getImplementationDifficulty());

        recommendationRepository.save(recommendation);
    }

    public List<Recommendation> getAllRecommendations() {
        return recommendationRepository.findAll();
    }

    public Recommendation getRecommendationById(int id) {
        return recommendationRepository.findById(id).orElseThrow(()
                -> new RuntimeException("No Recommendation found with id " + id));
    }

    public void updateRecommendation(Recommendation recommendation) throws RuntimeException {
        if (recommendationRepository.existsById(recommendation.getId()))
            recommendationRepository.save(recommendation);
        else
            throw new RuntimeException("Recommendation with id " + recommendation.getId() + " not found");
    }

    public void deleteRecommendationById(int id) throws RuntimeException {
        if (recommendationRepository.existsById(id))
            recommendationRepository.deleteById(id);
        else
            throw new RuntimeException("Recommendation with id " + id + " not found");
    }

}
