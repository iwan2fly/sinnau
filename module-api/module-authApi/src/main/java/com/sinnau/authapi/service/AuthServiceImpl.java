package com.sinnau.authapi.service;

import com.sinnau.authapi.model.LoginRequest;
import com.sinnau.authapi.model.LoginResponse;
import com.sinnau.authapi.model.SignupRequest;
import com.sinnau.authapi.model.SignupResponse;
import com.sinnau.authapi.security.JwtTokenProvider;
import com.sinnau.domain.user.entity.User;
import com.sinnau.domain.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public SignupResponse signup(SignupRequest signupRequest) {

        // 1. signupRequest 체크
        if ( signupRequest == null ) throw new IllegalArgumentException( "signupRequest is null" );
        if ( StringUtils.isEmpty( signupRequest.getEmail() )) throw new IllegalArgumentException( "email is empty" );
        if ( StringUtils.isEmpty( signupRequest.getPassword() )) throw new IllegalArgumentException( "password is empty" );
        if ( StringUtils.isEmpty( signupRequest.getName() )) throw new IllegalArgumentException( "name is empty" );

        // 2. 유저등록
        User user = userService.registerUser( signupRequest.getEmail(), signupRequest.getPassword(), signupRequest.getName() );
        log.info( user.toString() );

        // 3. 반환객체 생성 및 값 세팅
        SignupResponse signupResponse = new SignupResponse();
        signupResponse.setUser( user );
        signupResponse.setMessage( "사용자 등록이 완료되었습니다." );

        // 4 리턴
        return signupResponse;

    }


    public LoginResponse login(LoginRequest loginRequest) {

        // 1. loginRequest 체크
        if ( loginRequest == null ) throw new IllegalArgumentException( "loginRequest is null" );
        if ( StringUtils.isEmpty( loginRequest.getEmail() )) throw new IllegalArgumentException( "email is empty" );
        if ( StringUtils.isEmpty( loginRequest.getPassword() )) throw new IllegalArgumentException( "password is empty" );

        // 2. eamil 로 사용자 조회
        User user = userService.findByEmail( loginRequest.getEmail() );

        // 3. 비밀번호 비교 (수정된 부분)
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException( "password is incorrect" );
        }

        // 4. JWT 토큰 생성
        String token = jwtTokenProvider.generateToken(user.getEmail());

        // 5. 반환객체 생성 및 값 세팅
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUser( user );
        loginResponse.setToken( token );

        return loginResponse;
    }
}
