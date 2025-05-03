package com.sinnau.authapi.controller;

import com.sinnau.domain.auth.model.Token;
import com.sinnau.domain.auth.service.TokenService;
import com.sinnau.domain.user.model.User;
import com.sinnau.domain.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;  // 이 import 문 추가

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private static final Logger log = LoggerFactory.getLogger(AuthController.class);
  private final UserService userService;
  private final TokenService tokenService;

  public AuthController(UserService userService, TokenService tokenService) {
    this.userService = userService;
    this.tokenService = tokenService;
  }

  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody SignupRequest request) {

    try {
      // 이메일 중복 확인
      if (userService.isEmailExists(request.getEmail())) {
        return ResponseEntity.badRequest().body(new ErrorResponse("이미 사용 중인 이메일입니다."));
      }
      
      // 사용자 등록
      User user = userService.registerUser(
          request.getEmail(),
          request.getPassword(),
          request.getUsername() // 여기서는 username을 name으로 사용
      );
      
      return ResponseEntity.status(HttpStatus.CREATED).body(new SignupResponse(user.getId(), user.getEmail()));
    } catch (Exception e) {
      log.error("회원가입 실패", e);
      return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    try {
      // 사용자 인증
      User user = userService.authenticateUser(request.getUsername(), request.getPassword());
      
      if (user == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(new ErrorResponse("이메일 또는 비밀번호가 올바르지 않습니다."));
      }
      
      // 토큰 생성
      Token token = tokenService.generateToken(user);
      
      return ResponseEntity.ok(new LoginResponse(
          token.getAccessToken(),
          token.getRefreshToken(),
          token.getTokenType(),
          token.getExpiryDate()
      ));
    } catch (Exception e) {
      log.error("로그인 실패", e);
      return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }
  }

  // 응답 클래스들
  public static class SignupResponse {
    private Long id;
    private String email;

    public SignupResponse(Long id, String email) {
      this.id = id;
      this.email = email;
    }

    public Long getId() {
      return id;
    }

    public String getEmail() {
      return email;
    }
  }

  public static class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private LocalDateTime expiryDate;

    public LoginResponse(String accessToken, String refreshToken, String tokenType, LocalDateTime expiryDate) {
      this.accessToken = accessToken;
      this.refreshToken = refreshToken;
      this.tokenType = tokenType;
      this.expiryDate = expiryDate;
    }

    // Getters
    public String getAccessToken() {
      return accessToken;
    }

    public String getRefreshToken() {
      return refreshToken;
    }

    public String getTokenType() {
      return tokenType;
    }

    public LocalDateTime getExpiryDate() {
      return expiryDate;
    }
  }

  public static class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
      this.message = message;
    }

    public String getMessage() {
      return message;
    }
  }

  // 기존 Request 클래스들은 유지
  public static class SignupRequest {
    private String username;
    private String password;
    private String email;

    // Getters and Setters
    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }
    
    @Override
    public String toString() {
      return "SignupRequest{" +
          "username='" + username + '\'' +
          ", email='" + email + '\'' +
          ", password='[PROTECTED]'" +
          '}';
    }
  }

  public static class LoginRequest {
    private String username;
    private String password;

    // Getters and Setters
    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
    
    @Override
    public String toString() {
      return "LoginRequest{" +
          "username='" + username + '\'' +
          ", password='[PROTECTED]'" +
          '}';
    }
  }
}
