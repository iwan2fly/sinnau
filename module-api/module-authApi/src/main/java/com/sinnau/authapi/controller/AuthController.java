package com.sinnau.authapi.controller;

import com.sinnau.authapi.model.LoginResponse;
import com.sinnau.authapi.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinnau.authapi.model.LoginRequest;
import com.sinnau.authapi.model.SignupRequest;
import com.sinnau.authapi.model.SignupResponse;
import com.sinnau.common.model.CommonApiResponse; // 패키지 경로 정확함


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<CommonApiResponse<?>> signup(@RequestBody SignupRequest signupRequest) {

    log.info(signupRequest.toString());
    
    SignupResponse signupResponse = authService.signup(signupRequest);
    log.info( signupResponse.toString() );
    
    return ResponseEntity.ok(CommonApiResponse.success(signupResponse)); // CommonApiResponse로 감싸서 반환
  }

  @PostMapping("/login")
  public ResponseEntity<CommonApiResponse<?>> login(@RequestBody LoginRequest loginRequest) {

    LoginResponse loginResponse = authService.login(loginRequest);

    // TODO: Implement login logic
    return ResponseEntity.ok(CommonApiResponse.success(loginResponse)); // null 또는 적절한 응답 객체 전달
  }
}
