package com.example.foodpick.controller;

import com.example.foodpick.dto.auth.LoginRequestDto;
import com.example.foodpick.dto.auth.SignUpRequestDto;
import com.example.foodpick.dto.auth.TokenResponseDto;
import com.example.foodpick.dto.common.ApiResponse;
import com.example.foodpick.model.entity.User;
import com.example.foodpick.model.enums.Role;
import com.example.foodpick.security.jwt.JwtTokenProvider;
import com.example.foodpick.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequestDto signUpRequest) {
        User user = User.builder()
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .nickname(signUpRequest.getNickname())
                .cookingLevel(signUpRequest.getCookingLevel())
                .dietaryRestrictions(signUpRequest.getDietaryRestrictions())
                .role(Role.USER)
                .build();

        userService.createUser(user);
        return ResponseEntity.ok(ApiResponse.success("회원가입이 완료되었습니다.", null));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponseDto>> login(@Valid @RequestBody LoginRequestDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        String token = jwtTokenProvider.createToken(loginRequest.getEmail());
        TokenResponseDto tokenResponse = new TokenResponseDto(token, "Bearer");
        return ResponseEntity.ok(ApiResponse.success("로그인이 완료되었습니다.", tokenResponse));
    }
}
