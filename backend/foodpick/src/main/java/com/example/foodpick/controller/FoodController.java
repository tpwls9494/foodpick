package com.example.foodpick.controller;

import com.example.foodpick.dto.common.ApiResponse;
import com.example.foodpick.dto.food.FoodDto;
import com.example.foodpick.model.entity.Food;
import com.example.foodpick.repository.service.FoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Food>>> getAllFoods() {
        List<Food> foods = foodService.getAllFoods();
        return ResponseEntity.ok(ApiResponse.success(foods));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Food>> getFoodById(@PathVariable Long id) {
        Food food = foodService.getFoodById(id);
        return ResponseEntity.ok(ApiResponse.success(food));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<Food>>> getFoodsByCategory(@PathVariable String category) {
        List<Food> foods = foodService.getFoodsByCategory(category);
        return ResponseEntity.ok(ApiResponse.success(foods));
    }

    @GetMapping("/vegetarian")
    public ResponseEntity<ApiResponse<List<Food>>> getVegetarianFoods() {
        List<Food> foods = foodService.getVegetarianFoods();
        return ResponseEntity.ok(ApiResponse.success(foods));
    }

    @GetMapping("/difficulty/{maxDifficulty}")
    public ResponseEntity<ApiResponse<List<Food>>> getFoodsByDifficulty(
            @PathVariable Integer maxDifficulty) {
        List<Food> foods = foodService.getFoodsByDifficulty(maxDifficulty);
        return ResponseEntity.ok(ApiResponse.success(foods));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Food>> createFood(@Valid @RequestBody FoodDto foodDto) {
        Food food = foodService.createFood(foodDto);
        return ResponseEntity.ok(ApiResponse.success("음식이 등록되었습니다.", food));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Food>> updateFood(
            @PathVariable Long id,
            @Valid @RequestBody FoodDto foodDto) {
        Food food = foodService.updateFood(id, foodDto);
        return ResponseEntity.ok(ApiResponse.success("음식이 수정되었습니다.", food));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return ResponseEntity.ok(ApiResponse.success("음식이 삭제되었습니다.", null));
    }
}
