package com.example.foodpick.repository;

import com.example.foodpick.model.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByFoodId(Long foodId);
    Optional<Recipe> findByFoodIdAndServings(Long foodId, Integer servings);
}
