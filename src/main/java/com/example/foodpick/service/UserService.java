package com.example.foodpick.service;

import com.example.foodpick.dto.user.UserProfileDto;
import com.example.foodpick.exception.BaseException;
import com.example.foodpick.exception.ErrorCode;
import com.example.foodpick.model.entity.User;
import com.example.foodpick.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 서비스 계층
 * 비즈니스 로직을 처리하는 클래스
 * 회원가입, 로그인 등의 구체적인 동작 정의
 */



@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(User user) {
        // 이메일 중복 검사
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BaseException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        // 닉네임 중복 검사
        if (userRepository.existsByNickname(user.getNickname())) {
            throw new BaseException(ErrorCode.NICKNAME_ALREADY_EXISTS);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
    }

    @Transactional
    public User updateUser(User user) {
        // 사용자 존재 여부 확인
        if (!userRepository.existsById(user.getId())) {
            throw new BaseException(ErrorCode.USER_NOT_FOUND);
        }
        return userRepository.save(user);
    }

    @Transactional
    public User updateProfile(String email, UserProfileDto profileDto) {
        User user = getUserByEmail(email);

        if (profileDto.getNickname() != null &&
            !user.getNickname().equals(profileDto.getNickname()) &&
                userRepository.existsByNickname(profileDto.getNickname())) {
            throw new BaseException(ErrorCode.NICKNAME_ALREADY_EXISTS);
        }

        user.setNickname(profileDto.getNickname());
        user.setCookingLevel(profileDto.getCookingLevel());
        user.setDietaryRestrictions(profileDto.getDietaryRestrictions());

        return userRepository.save(user);
    }
}
