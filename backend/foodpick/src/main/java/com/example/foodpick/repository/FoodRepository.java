package com.example.foodpick.repository;

import com.example.foodpick.model.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByCategory(String category);
    List<Food> findByDifficultyLessThanEqual(Integer difficulty);
    List<Food> findByCookingTimeLessThanEqual(Integer cookingTime);
    List<Food> findByIsVegetarianTrue();
    List<Food> findByIsSpicyFalse();
}
