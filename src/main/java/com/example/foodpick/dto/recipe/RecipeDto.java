package com.example.foodpick.dto.recipe;

import com.example.foodpick.model.entity.Recipe;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {
    private Long id;

    @NotNull(message = "음식 ID는 필수입니다.")
    private Long foodId;

    @NotBlank(message = "조리 방법은 필수입니다.")
    private String instructions;

    private String tips;
    private Integer servings;

    public static RecipeDto from(Recipe recipe){
        return RecipeDto.builder()
                .id(recipe.getId())
                .foodId(recipe.getFood().getId())
                .instructions(recipe.getInstructions())
                .tips(recipe.getTips())
                .servings(recipe.getServings())
                .build();
    }
}