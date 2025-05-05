package com.sinnau.authapi.service;

import com.sinnau.authapi.model.SignupRequest;
import com.sinnau.authapi.model.SignupResponse;

import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    public SignupResponse signup(SignupRequest signupRequest) {
        // TODO
        // 1. signupRequest 체크
        if ( signupRequest == null ) throw new IllegalArgumentException( "signupRequest is null" );
        if ( StringUtils.isEmpty( signupRequest.getEmail() )) throw new IllegalArgumentException( "email is empty" );
        if ( StringUtils.isEmpty( signupRequest.getPassword() )) throw new IllegalArgumentException( "password is empty" );
        if ( StringUtils.isEmpty( signupRequest.getName() )) throw new IllegalArgumentException( "name is empty" );

        // 2. 유저등록


        // 3. 반환객체 생성 및 값 세팅
        SignupResponse signupResponse = new SignupResponse();

        // 4 리턴
        return signupResponse;

    }
}
