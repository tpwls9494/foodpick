package com.example.foodpick.dto.preference;

import com.example.foodpick.model.entity.UserPreference;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PreferenceResponseDto {
    private Long id;
    private String foodName;
    private boolean liked;
    private LocalDateTime createdAt;

    public static PreferenceResponseDto from(UserPreference preference) {
        return PreferenceResponseDto.builder()
                .id(preference.getId())
                .foodName(preference.getFood().getName())
                .liked(preference.isLiked())
                .createdAt(preference.getCreatedAt())
                .build();
    }
}
