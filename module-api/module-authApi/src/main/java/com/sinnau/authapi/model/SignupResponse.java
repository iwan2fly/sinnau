package com.sinnau.authapi.model;

import com.sinnau.domain.user.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SignupResponse {
    private User user;
    private String message = "회원가입이 성공적으로 완료되었습니다";
}
