# FoodPick - AI 기반 개인 맞춤형 음식 추천 플랫폼

## 📌 프로젝트 개요
- 1인 가구를 위한 맞춤형 음식 및 레시피 추천 플랫폼 개발
- 개발 기간: 2024.01 ~ 2024.02 (1개월)
- 개인 프로젝트 (Backend 개발)

## 🛠 기술 스택
<div align="left">
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens">
</div>

### Backend
- Java 17
- Spring Boot 3.2.1
- Spring Security
- JPA/Hibernate
- MySQL 8.0
- JWT

### Frontend (예정)
- React (Web)
- React Native (Mobile)

## 💡 주요 기능

### 사용자 인증 시스템
- JWT 기반 회원가입/로그인
- 사용자 프로필 관리
- Spring Security 기반 인증/인가

### 음식/레시피 관리
- 음식 정보 CRUD 기능
- 레시피 등록 및 관리
- 카테고리별 음식 조회

### 추천 시스템
- 사용자 선호도 기반 추천
- 난이도별 맞춤 추천
- 조리 시간 기반 추천

## 🧠 추천 시스템 알고리즘
- **카테고리 기반**: 사용자가 좋아하는 음식 카테고리 분석
- **난이도 기반**: 사용자 요리 실력에 맞는 음식 추천  
- **조리 시간**: 빠르게 만들 수 있는 음식 우선 추천
- **선호도 분석**: 사용자의 좋아요/싫어요 패턴 분석

## 📋 API 명세

### 인증 API
| Method | URI | Description |
|:---:|:---:|:---:|
| POST | /api/auth/signup | 회원가입 |
| POST | /api/auth/login | 로그인 |

### 사용자 API
| Method | URI | Description |
|:---:|:---:|:---:|
| GET | /api/users/me | 내 프로필 조회 |
| PUT | /api/users/me | 프로필 수정 |

### 음식 API
| Method | URI | Description |
|:---:|:---:|:---:|
| GET | /api/foods | 전체 음식 조회 |
| POST | /api/foods | 새로운 음식 등록 |
| GET | /api/foods/{id} | 특정 음식 조회 |
| PUT | /api/foods/{id} | 음식 정보 수정 |
| DELETE | /api/foods/{id} | 음식 삭제 |

### 레시피 API
| Method | URI | Description |
|:---:|:---:|:---:|
| GET | /api/recipes | 모든 레시피 조회 |
| GET | /api/recipes/{id} | 특정 레시피 조회 |
| GET | /api/recipes/food/{foodId} | 특정 음식의 레시피 목록 |
| POST | /api/recipes/food/{foodId} | 레시피 등록 |
| PUT | /api/recipes/{id} | 레시피 수정 |
| DELETE | /api/recipes/{id} | 레시피 삭제 |

### 추천 API
| Method | URI | Description |
|:---:|:---:|:---:|
| GET | /api/recommendations | 사용자 맞춤 음식 추천 |
| POST | /api/recommendations/preference/{foodId} | 음식 선호도 저장 |

## 🗂 프로젝트 구조
```
src/main/java/com/example/foodpick
├── config        // Security, JWT 설정
├── controller    // API 엔드포인트
├── dto          // 데이터 전송 객체
├── exception    // 예외 처리
├── model        // 엔티티
├── repository   // 데이터 접근 계층
├── security     // 보안 관련
└── service      // 비즈니스 로직
```

## 📝 진행 상황

### 2024-01-24
- ✅ 프로젝트 초기 설정
- ✅ 기본 엔티티 설계
- ✅ Spring Security 및 JWT 설정

### 2024-01-25
- ✅ 사용자 인증 시스템 구현
- ✅ 음식 관련 API 구현
- ✅ 예외 처리 구현

### 2024-01-26
- ✅ 추천 시스템 기본 구조 구현
- ✅ UserPreference 엔티티 생성
- ✅ RecommendationService 개발
- ✅ RecommendationController 추가
- ✅ 사용자 선호도 저장 및 음식 추천 API 구현

### 2024-01-27
- ✅ 레시피 CRUD API 구현
- ✅ 개선된 추천 알고리즘 개발
  - 사용자 요리 실력 고려
  - 조리 시간 기반 추천
  - 선호 카테고리 분석
- ✅ API 테스트 및 오류 수정
- ✅ API 문서 정리

## ⚙️ 환경 설정
- IDE: IntelliJ IDEA
- Java Version: 17
- Spring Boot Version: 3.2.1
- Database: MySQL 8.0

## 🚀 설치 및 실행 방법

### 요구사항
- Java 17
- MySQL 8.0

### 설치
```bash
# 저장소 클론
git clone https://github.com/yourusername/foodpick.git

# MySQL 데이터베이스 생성
CREATE DATABASE foodpick;

# 프로젝트 실행
./gradlew bootRun
```

## 🗓️ 향후 개발 계획
- 안드로이드 애플리케이션 개발
  - 음식 스와이프 인터페이스
  - 사용자 프로필 관리
  - 레시피 상세 보기
- 추천 알고리즘 고도화
  - 협업 필터링 추가
  - 복합 요소 기반 추천
- 데이터 증대
  - 다양한 카테고리 음식 데이터 추가
  - 상세 레시피 정보 구축