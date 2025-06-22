package com.lakshan.carbonwise.service;

import com.lakshan.carbonwise.entity.Recommendation;
import com.lakshan.carbonwise.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final AiService aiService;

    @Autowired
    public RecommendationService(RecommendationRepository recommendationRepository, AiService aiService) {
        this.recommendationRepository = recommendationRepository;
        this.aiService = aiService;
    }

    public void addNewRecommendation(String recommendationPrompt) {
        String chatResponse = aiService.chat(recommendationPrompt);
        System.out.println(chatResponse);

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
