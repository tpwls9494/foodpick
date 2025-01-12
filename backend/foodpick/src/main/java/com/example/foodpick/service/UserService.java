package com.example.foodpick.service;

import com.example.foodpick.exception.BaseException;
import com.example.foodpick.exception.ErrorCode;
import com.example.foodpick.model.entity.User;
import com.example.foodpick.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
