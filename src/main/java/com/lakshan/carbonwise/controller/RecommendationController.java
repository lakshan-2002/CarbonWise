package com.lakshan.carbonwise.controller;

import com.lakshan.carbonwise.entity.Recommendation;
import com.lakshan.carbonwise.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping
    public List<Recommendation> getRecommendations() {
        return recommendationService.getAllRecommendations();
    }

    @GetMapping("{id}")
    public Recommendation getRecommendation(@PathVariable int id) {
        return recommendationService.getRecommendationById(id);
    }

    @GetMapping("/getRecommendationsByEmissionId/{emissionId}")
    public List<Recommendation> getRecommendationsByEmissionId(@PathVariable int emissionId) {
        return recommendationService.getRecommendationsByEmissionId(emissionId);
    }
}
