package com.example.foodpick.controller;

import com.example.foodpick.dto.common.ApiResponse;
import com.example.foodpick.dto.recipe.RecipeDto;
import com.example.foodpick.model.entity.Recipe;
import com.example.foodpick.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RecipeDto>>> getAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        List<RecipeDto> recipeDtos = recipes.stream()
                .map(RecipeDto::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(recipeDtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Recipe>> getRecipeById(@PathVariable("id") Long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok(ApiResponse.success(recipe));
    }

    @GetMapping("/food/{foodId}")
    public ResponseEntity<ApiResponse<List<Recipe>>> getRecipesByFoodId(
            @PathVariable("foodId") Long foodId) {
        List<Recipe> recipes = recipeService.getRecipesByFoodId(foodId);
        return ResponseEntity.ok(ApiResponse.success(recipes));
    }

    @PostMapping("/food/{foodId}")
    public ResponseEntity<ApiResponse<Recipe>> createRecipe(
            @PathVariable("foodId") Long foodId,
            @RequestBody RecipeDto recipeDto) {
        Recipe recipe = recipeService.createRecipe(foodId, recipeDto);
        return ResponseEntity.ok(ApiResponse.success(recipe));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Recipe>> updateRecipe(
            @PathVariable("id") Long id,
            @RequestBody RecipeDto recipeDto) {
        Recipe recipe = recipeService.updateRecipe(id, recipeDto);
        return ResponseEntity.ok(ApiResponse.success("레시피가 수정되었습니다.", recipe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRecipe(@PathVariable("id") Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.ok(ApiResponse.success("레시피가 삭제되었습니다.", null));
    }
}
