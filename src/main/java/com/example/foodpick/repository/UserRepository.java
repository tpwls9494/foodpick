package com.example.foodpick.repository;

import com.example.foodpick.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 저장소 계층
 * 데이터베이스 작업을 처리하는 클래스
 */


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNickname(String email);
}
