# 브랜치 전략

## 브랜치 구조
- `main`: 프로덕션 브랜치
- `develop`: 개발 브랜치
- `feature/*`: 기능 개발 브랜치
- `bugfix/*`: 버그 수정 브랜치
- `hotfix/*`: 긴급 수정 브랜치

## 브랜치 생성 규칙
1. 기능 개발
   ```
   git checkout -b feature/기능명 develop
   ```

2. 버그 수정
   ```
   git checkout -b bugfix/버그명 develop
   ```

3. 긴급 수정
   ```
   git checkout -b hotfix/수정명 main
   ```

## 브랜치 머지 규칙
1. feature/bugfix → develop
   - PR 생성 후 코드 리뷰
   - CI 통과 확인
   - 리뷰어 승인 후 머지

2. hotfix → main
   - PR 생성 후 코드 리뷰
   - CI 통과 확인
   - 리뷰어 승인 후 머지
   - main → develop 머지

## 릴리즈 프로세스
1. develop → main 머지
2. 버전 태그 생성
3. 배포 