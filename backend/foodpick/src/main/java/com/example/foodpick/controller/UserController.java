package com.example.foodpick.controller;

import com.example.foodpick.dto.common.ApiResponse;
import com.example.foodpick.dto.user.UserProfileDto;
import com.example.foodpick.model.entity.User;
import com.example.foodpick.repository.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * 사용자 프로필 관리를 위한 유저 서비스 컨트롤
 */



@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserProfileDto>> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(authentication.getName());
        UserProfileDto profileDto = UserProfileDto.from(user);
        return ResponseEntity.ok(ApiResponse.success(profileDto));
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<UserProfileDto>> updateMyProfile(
            @RequestBody UserProfileDto profileDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User updateUser = userService.updateProfile(authentication.getName(), profileDto);
        return ResponseEntity.ok(ApiResponse.success(UserProfileDto.from(updateUser)));
    }
}
