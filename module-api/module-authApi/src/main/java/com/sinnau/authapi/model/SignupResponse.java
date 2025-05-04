package com.sinnau.authapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupResponse {
    private String name;
    private String email;
    private String message = "회원가입이 성공적으로 완료되었습니다";
}
