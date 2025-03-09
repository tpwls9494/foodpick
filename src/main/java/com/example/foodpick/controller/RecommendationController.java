package com.example.foodpick.controller;

import com.example.foodpick.dto.common.ApiResponse;
import com.example.foodpick.dto.preference.PreferenceResponseDto;
import com.example.foodpick.model.entity.Food;
import com.example.foodpick.model.entity.User;
import com.example.foodpick.model.entity.UserPreference;
import com.example.foodpick.service.RecommendationService;
import com.example.foodpick.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Food>>> getRecommendations() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(authentication.getName());
        List<Food> recommendations = recommendationService.recommendFoodForUser(user);
        return ResponseEntity.ok(ApiResponse.success(recommendations));
    }

    @PostMapping("/preference/{foodId}")
    public ResponseEntity<ApiResponse<PreferenceResponseDto>> savePreference(
            @PathVariable("foodId") Long foodId,
            @RequestParam("liked") boolean liked) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(authentication.getName());
        UserPreference preference = recommendationService.savePreference(user, foodId, liked);
        return ResponseEntity.ok(ApiResponse.success("선호도가 저장되었습니다.",
                PreferenceResponseDto.from(preference)));
    }
}
