package com.example.foodpick.service;

import com.example.foodpick.dto.recipe.RecipeDto;
import com.example.foodpick.exception.BaseException;
import com.example.foodpick.exception.ErrorCode;
import com.example.foodpick.model.entity.Food;
import com.example.foodpick.model.entity.Recipe;
import com.example.foodpick.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final FoodService foodService;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.RECIPE_NOT_FOUND));
    }

    public List<Recipe> getRecipesByFoodId(Long foodId) {
        foodService.getFoodById(foodId);
        return recipeRepository.findByFoodId(foodId);
    }

    @Transactional
    public Recipe createRecipe(Long foodId, RecipeDto recipeDto) {
        Food food = foodService.getFoodById(foodId);
        Recipe recipe = Recipe.builder()
                .food(food)
                .instructions(recipeDto.getInstructions())
                .tips(recipeDto.getTips())
                .servings(recipeDto.getServings())
                .build();
        return recipeRepository.save(recipe);
    }

    @Transactional
    public Recipe updateRecipe(Long id, RecipeDto recipeDto) {
        Recipe recipe = getRecipeById(id);

        recipe.setInstructions(recipeDto.getInstructions());
        recipe.setTips(recipeDto.getTips());
        recipe.setServings(recipeDto.getServings());

        return recipeRepository.save(recipe);
    }

    @Transactional
    public void deleteRecipe(Long id) {  // 새로운 메서드 추가
        Recipe recipe = getRecipeById(id);
        recipeRepository.delete(recipe);
    }
}
