package com.lakshan.carbonwise.controller;

import com.lakshan.carbonwise.annotation.AuthRequired;
import com.lakshan.carbonwise.annotation.CurrentUser;
import com.lakshan.carbonwise.entity.Recommendation;
import com.lakshan.carbonwise.entity.User;
import com.lakshan.carbonwise.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping
    @AuthRequired
    public List<Recommendation> getRecommendations(@CurrentUser User user) {
        return recommendationService.getRecommendationByUserId(user.getId());
    }

//    @GetMapping("{id}")
//    public Recommendation getRecommendation(@PathVariable int id) {
//        return recommendationService.getRecommendationById(id);
//    }

    @GetMapping("/getRecommendationsByEmissionId/{emissionId}")
    public List<Recommendation> getRecommendationsByEmissionId(@PathVariable int emissionId) {
        return recommendationService.getRecommendationsByEmissionId(emissionId);
    }

}
