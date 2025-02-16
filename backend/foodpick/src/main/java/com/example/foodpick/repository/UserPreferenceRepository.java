package com.example.foodpick.repository;

import com.example.foodpick.model.entity.Food;
import com.example.foodpick.model.entity.User;
import com.example.foodpick.model.entity.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
    List<UserPreference> findByUser(User user);
    Optional<UserPreference> findByUserAndFood(User user, Food food);
    List<UserPreference> findByUserAndLiked(User user, boolean liked);
}
