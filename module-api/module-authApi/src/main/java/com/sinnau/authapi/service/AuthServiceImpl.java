package com.sinnau.authapi.service;

import com.sinnau.authapi.model.SignupRequest;
import com.sinnau.authapi.model.SignupResponse;
import com.sinnau.domain.user.service.UserService; // UserService import 추가
import org.apache.commons.lang3.StringUtils; // StringUtils import 추가

import lombok.RequiredArgsConstructor; // RequiredArgsConstructor 추가
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // 생성자 주입을 위한 어노테이션 추가
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
