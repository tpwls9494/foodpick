package com.example.foodpick.model.entity;


import com.example.foodpick.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    private String profileImage;

    @ElementCollection
    private List<String> favoriteFood = new ArrayList<>();

    @ElementCollection
    private List<String> dislikedFood = new ArrayList<>();

    private Integer cookingLevel;

    @Column(length = 500)
    private String dietaryRestrictions;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

}
