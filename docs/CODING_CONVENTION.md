# 코딩 컨벤션

## Java 코딩 스타일
- Google Java Style Guide를 따릅니다.
- Spotless를 사용하여 자동 포맷팅을 적용합니다.

## 네이밍 규칙
- 클래스: PascalCase
- 메서드: camelCase
- 변수: camelCase
- 상수: UPPER_SNAKE_CASE
- 패키지: 소문자

## 주석 규칙
- JavaDoc을 사용하여 public API 문서화
- 복잡한 로직에 대한 설명 주석 추가
- TODO, FIXME 등의 태그 사용

## 테스트 작성 규칙
- 테스트 클래스명: `{클래스명}Test`
- 테스트 메서드명: `should_{기대하는_행동}_when_{조건}`
- 각 테스트는 독립적으로 실행 가능해야 함

## 커밋 메시지 규칙
- feat: 새로운 기능
- fix: 버그 수정
- docs: 문서 변경
- style: 코드 스타일 변경
- refactor: 코드 리팩토링
- test: 테스트 코드
- chore: 빌드 프로세스, 도구 변경 