package com.sinnau.authapi.controller;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinnau.authapi.model.LoginRequest;
import com.sinnau.authapi.model.SignupRequest;
import com.sinnau.authapi.model.SignupResponse;
import com.sinnau.common.api.model.CommonApiResponse; // 패키지 경로 정확함
import com.sinnau.

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<CommonApiResponse<?>> signup(@RequestBody SignupRequest request) {

    log.info(request.toString());
    
    // 응답 객체 생성 (DTO 패턴)
    SignupResponse signupResponse = authService.signup(request);

    
    return ResponseEntity.ok(CommonApiResponse.success(response)); // CommonApiResponse로 감싸서 반환
  }

  @PostMapping("/login")
  public ResponseEntity<CommonApiResponse<?>> login(@RequestBody LoginRequest request) {
    // TODO: Implement login logic
    return ResponseEntity.ok(CommonApiResponse.success(null)); // null 또는 적절한 응답 객체 전달
  }
}
