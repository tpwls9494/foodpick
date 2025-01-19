package com.example.foodpick.repository.service;

import com.example.foodpick.exception.BaseException;
import com.example.foodpick.exception.ErrorCode;
import com.example.foodpick.model.entity.Food;
import com.example.foodpick.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * food 엔티티의 서비스를 설정하는 foodService
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodService {
    private final FoodRepository foodRepository;

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Food getFoodById(Long id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.FOOD_NOT_FOUND));
    }

    @Transactional
    public Food createFood(Food food) {
        return foodRepository.save(food);
    }

    @Transactional
    public Food updateFood(Long id, Food foodDetails) {
        Food food = getFoodById(id);

        food.setName(foodDetails.getName());
        food.setDescription(foodDetails.getDescription());
        food.setCategory(foodDetails.getCategory());
        food.setImageUrl(foodDetails.getImageUrl());
        food.setCookingTime(foodDetails.getCookingTime());
        food.setDifficulty(foodDetails.getDifficulty());
        food.setIngredients(foodDetails.getIngredients());
        food.setIsSpicy(foodDetails.getIsSpicy());
        food.setIsVegetarian(foodDetails.getIsVegetarian());

        return foodRepository.save(food);
    }

    public List<Food> getFoodsByCategory(String category) {
        return foodRepository.findByCategory(category);
    }

    public List<Food> getFoodsByDifficulty(Integer maxDifficulty) {
        return foodRepository.findByDifficultyLessThanEqual(maxDifficulty);
    }

    public List<Food> getVegetarianFoods() {
        return foodRepository.findByIsVegetarianTrue();
    }
}
