package com.sinnau.authapi.service;

import com.sinnau.authapi.model.SignupRequest;
import com.sinnau.authapi.model.SignupResponse;
import com.sinnau.domain.user.model.User;
import com.sinnau.domain.user.service.UserService;
import org.apache.commons.lang3.StringUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    public SignupResponse signup(SignupRequest signupRequest) {

        // 1. signupRequest 체크
        if ( signupRequest == null ) throw new IllegalArgumentException( "signupRequest is null" );
        if ( StringUtils.isEmpty( signupRequest.getEmail() )) throw new IllegalArgumentException( "email is empty" );
        if ( StringUtils.isEmpty( signupRequest.getPassword() )) throw new IllegalArgumentException( "password is empty" );
        if ( StringUtils.isEmpty( signupRequest.getName() )) throw new IllegalArgumentException( "name is empty" );

        // 2. 유저등록
        User user = userService.registerUser( signupRequest.getEmail(), signupRequest.getPassword(), signupRequest.getName() );

        // 3. 반환객체 생성 및 값 세팅
        SignupResponse signupResponse = new SignupResponse();
        signupResponse.setUser( user );
        signupResponse.setMessage( "사용자 등록이 완료되었습니다." );

        // 4 리턴
        return signupResponse;

    }
}
