package com.example.foodpick.dto.user;

import com.example.foodpick.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {
    private String nickname;
    private Integer cookingLevel;
    private String dietaryRestrictions;

    public static UserProfileDto from(User user) {
        return UserProfileDto.builder()
                .nickname(user.getNickname())
                .cookingLevel(user.getCookingLevel())
                .dietaryRestrictions(user.getDietaryRestrictions())
                .build();
    }
}
