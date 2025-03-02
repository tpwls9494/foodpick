package com.example.foodpick.service;

import com.example.foodpick.model.entity.Food;
import com.example.foodpick.model.entity.User;
import com.example.foodpick.model.entity.UserPreference;
import com.example.foodpick.repository.FoodRepository;
import com.example.foodpick.repository.UserPreferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<UserPreference> userPreferences = preferenceRepository.findByUser(user);

        // 사용자가 좋아한 음식들의 카테고리 분석
        Map<String, Long> categoryPrefereences = userPreferences.stream()
                .filter(UserPreference::isLiked)
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

        // 추천 음식 찾기
        if (favoriteCategory != null) {
            List<Food> ratedFoods = userPreferences.stream()
                    .map(UserPreference::getFood)
                    .collect(Collectors.toList());

            return foodRepository.findByCategory(favoriteCategory).stream()
                    .filter(food -> !ratedFoods.contains(food))
                    .limit(5)
                    .collect(Collectors.toList());
        }

        // 선호도 데이터가 없는 경우 기본 추천
        return foodRepository.findAll().stream()
                .limit(5)
                .collect(Collectors.toList());
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
