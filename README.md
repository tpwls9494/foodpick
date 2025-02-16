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

### 1. 사용자 인증 시스템
- JWT 기반 회원가입/로그인
- 사용자 프로필 관리
- Spring Security 기반 인증/인가

### 2. 음식/레시피 관리
- 음식 정보 CRUD 기능
- 레시피 등록 및 관리
- 카테고리별 음식 조회

### 3. 추천 시스템
- 사용자 선호도 기반 추천
- 난이도별 맞춤 추천
- 조리 시간 기반 추천

## 📋 API 명세

### 인증 API
| Method | URI | Description |
|:---:|:---:|:---:|
| POST | /api/auth/signup | 회원가입 |
| POST | /api/auth/login | 로그인 |

### 음식 API
| Method | URI | Description |
|:---:|:---:|:---:|
| GET | /api/foods | 전체 음식 조회 |
| POST | /api/foods | 새로운 음식 등록 |
| GET | /api/foods/{id} | 특정 음식 조회 |
| PUT | /api/foods/{id} | 음식 정보 수정 |
| DELETE | /api/foods/{id} | 음식 삭제 |

### 추천 API
| Method | URI | Description |
|:---:|:---:|:---:|
| GET | /api/recommendations | 사용자 맞춤 음식 추천 |
| POST | /api/recommendations/preference/{foodId} | 음식 선호도 저장 |

## 🗂 프로젝트 구조
```tree
foodpick/
├── backend/
│   ├── src/main/java/
│   │   └── com/example/foodpick/
│   │       ├── config/
│   │       ├── controller/
│   │       ├── dto/
│   │       ├── exception/
│   │       ├── model/
│   │       ├── repository/
│   │       ├── security/
│   │       └── service/
│   └── src/main/resources/
└── frontend/ (예정)
```

## 📝 진행 상황
#### 2024-01-24
- ✅ 프로젝트 초기 설정
- ✅ 기본 엔티티 설계
- ✅ Spring Security 및 JWT 설정

#### 2024-01-25
- ✅ 사용자 인증 시스템 구현
- ✅ 음식 관련 API 구현
- ✅ 예외 처리 구현

#### 2024-01-26
- ⭕ 추천 시스템 구현 중
- ⭕ 선호도 데이터 구조 설계
- ⭕ 기본 추천 알고리즘 구현

## 🚀 설치 및 실행 방법

### 요구사항
- Java 17
- MySQL 8.0

### 설치
1. 저장소 클론
```bash
git clone https://github.com/yourusername/foodpick.git

# MySQL 데이터베이스 생성
CREATE DATABASE foodpick;

# 프로젝트 실행
./gradlew bootRun
