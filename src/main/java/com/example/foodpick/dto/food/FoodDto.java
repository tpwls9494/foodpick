package com.example.foodpick.dto.food;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FoodDto {
    @NotBlank(message = "음식 이름은 필수입니다.")
    private String name;

    private String description;
    private String category;
    private String imageUrl;

    @NotNull(message = "조리 시간은 필수입니다.")
    private  Integer cookingTime;

    @NotNull(message = "난이도는 필수입니다.")
    private Integer difficulty;

    private String ingredients;
    private Boolean isSpicy = false;
    private Boolean isVegetarian = false;
}
