package com.example.foodpick.controller;

import com.example.foodpick.dto.common.ApiResponse;
import com.example.foodpick.dto.recipe.RecipeDto;
import com.example.foodpick.model.entity.Recipe;
import com.example.foodpick.repository.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Recipe>>> getAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        return ResponseEntity.ok(ApiResponse.success(recipes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Recipe>> getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok(ApiResponse.success(recipe));
    }

    @GetMapping("/food/{foodId}")
    public ResponseEntity<ApiResponse<List<Recipe>>> getRecipesByFoodId(
            @PathVariable Long foodId) {
        List<Recipe> recipes = recipeService.getRecipesByFoodId(foodId);
        return ResponseEntity.ok(ApiResponse.success(recipes));
    }

    @PostMapping("/food/{foodId}")
    public ResponseEntity<ApiResponse<Recipe>> createRecipe(
            @PathVariable Long foodId,
            @RequestBody RecipeDto recipeDto) {
        Recipe recipe = recipeService.createRecipe(foodId, recipeDto);
        return ResponseEntity.ok(ApiResponse.success(recipe));
    }
}
