# Seenna Project

## 프로젝트 소개
사용자 인증 및 권한 관리를 위한 모듈형 백엔드 시스템입니다.

## 프로젝트 구조

### module-domain
- **module-user**: 사용자 관리 및 권한 관련 도메인 모델
  - User: 사용자 정보 관리
  - Role: 사용자 권한 관리

- **module-auth**: 인증 관련 도메인 모델
  - Token: 접근 토큰 및 갱신 토큰 관리

### module-application
애플리케이션 계층 로직 구현

### module-common
공통 유틸리티 및 설정

## 기술 스택
- Java
- Spring Boot
- Gradle