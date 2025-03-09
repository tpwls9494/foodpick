package com.example.foodpick.service;

import com.example.foodpick.model.entity.Food;
import com.example.foodpick.model.entity.User;
import com.example.foodpick.model.entity.UserPreference;
import com.example.foodpick.repository.FoodRepository;
import com.example.foodpick.repository.UserPreferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecommendationService {
    private final UserPreferenceRepository preferenceRepository;
    private final FoodRepository foodRepository;

    public List<Food> recommendFoodForUser(User user) {
        // 사용자의 선호도 데이터 가져오기
        List<Long> ratedFoodIds = preferenceRepository.findByUser(user).stream()
                .map(pref -> pref.getFood().getId())
                .collect(Collectors.toList());

        // 사용자가 좋아한 음식들의 카테고리 분석
        Map<String, Long> categoryPrefereences = preferenceRepository.findByUserAndLiked(user, true).stream()
                .map(pref -> pref.getFood().getCategory())
                .collect(Collectors.groupingBy(
                        category -> category,
                        Collectors.counting()
                ));

        // 가장 선호하는 카테고리 찾기
        String favoriteCategory = categoryPrefereences.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        // 2. 난이도 기반 추천 - 사용자 요리 실력 고려
        Integer maxDifficulty = user.getCookingLevel() != null ? user.getCookingLevel() + 1 : 5;

        // 3. 추천 음식 찾기 (카테고리 + 난이도 + 평가 안한 음식)
        List<Food> recommendations = new ArrayList<>();

        // 추천 음식 찾기
        if (favoriteCategory != null) {
            // 선호 카테고리에서 추천
            recommendations = foodRepository.findByCategoryAndDifficultyLessThanEqual(
                            favoriteCategory, maxDifficulty).stream()
                    .filter(food -> !ratedFoodIds.contains(food.getId()))
                    .limit(3)
                    .collect(Collectors.toList());
        }

        // 4. 빠른 조리 시간 음식 추가 (15분 이내)
        if (recommendations.size() < 5) {
            final List<Food> currentRecommendations = recommendations; // 람다에서 참조할 final 변수
            List<Food> quickRecipes = foodRepository.findByCookingTimeLessThanAndDifficultyLessThanEqual(
                            15, maxDifficulty).stream()
                    .filter(food -> !ratedFoodIds.contains(food.getId()) && !currentRecommendations.contains(food))
                    .limit(5 - recommendations.size())
                    .collect(Collectors.toList());
                recommendations.addAll(quickRecipes);
            }

        // 5. 부족한 경우 일반 추천 추가
        if (recommendations.size() < 5) {
            final List<Food> currentRecommendations = recommendations; // 람다에서 참조할 final 변수
            List<Food> generalRecommendations = foodRepository.findByCookingTimeLessThanEqual(maxDifficulty).stream()
                    .filter(food -> !ratedFoodIds.contains(food.getId()) && !currentRecommendations.contains(food))
                    .limit(5 - recommendations.size())
                    .collect(Collectors.toList());
                recommendations.addAll(generalRecommendations);
            }
            return recommendations;
        }

    @Transactional
    public UserPreference savePreference(User user, Long foodId, boolean liked) {
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        UserPreference preference = UserPreference.builder()
                .user(user)
                .food(food)
                .liked(liked)
                .build();

        return preferenceRepository.save(preference);
    }
}
