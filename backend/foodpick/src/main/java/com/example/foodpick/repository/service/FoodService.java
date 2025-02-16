package com.example.foodpick.repository.service;

import com.example.foodpick.dto.food.FoodDto;
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

    @Transactional
    public Food createFood(FoodDto foodDto) {
        Food food = Food.builder()
                .name(foodDto.getName())
                .description(foodDto.getDescription())
                .category(foodDto.getCategory())
                .imageUrl(foodDto.getImageUrl())
                .cookingTime(foodDto.getCookingTime())
                .difficulty(foodDto.getDifficulty())
                .ingredients(foodDto.getIngredients())
                .isSpicy(foodDto.getIsSpicy())
                .isVegetarian(foodDto.getIsVegetarian())
                .build();
        return foodRepository.save(food);
    }

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Food getFoodById(Long id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.FOOD_NOT_FOUND));
    }


    @Transactional
    public Food updateFood(Long id, FoodDto foodDto) {
        Food food = getFoodById(id);
        food.setName(foodDto.getName());
        food.setDescription(foodDto.getDescription());
        food.setCategory(foodDto.getCategory());
        food.setImageUrl(foodDto.getImageUrl());
        food.setCookingTime(foodDto.getCookingTime());
        food.setDifficulty(foodDto.getDifficulty());
        food.setIngredients(foodDto.getIngredients());
        food.setIsSpicy(foodDto.getIsSpicy());
        food.setIsVegetarian(foodDto.getIsVegetarian());

        return foodRepository.save(food);
    }

    @Transactional
    public void deleteFood(Long id) {
        foodRepository.delete(getFoodById(id));
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
