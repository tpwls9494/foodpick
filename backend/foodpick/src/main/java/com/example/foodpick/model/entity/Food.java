package com.example.foodpick.model.entity;

import com.example.foodpick.dto.recipe.RecipeDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Food의 엔티티
 */

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    private String category;

    private String imageUrl;

    private Integer cookingTime;

    private Integer difficulty;

    @Column(length = 2000)
    private String ingredients;

    private Boolean isSpicy;
    private Boolean isVegetarian;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<Recipe> recipes;
}
