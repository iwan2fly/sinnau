package com.sinnau.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SinnauErrorCode {

    UNDEFINED   ( 0, ""),

    MEMBER_NOT_FOUND( 2000, "회원이 존재하지 않습니다." ),
    ALREADY_REGISTERED_EMAIL( 2001, "이미 등록되어 있는 이메일 주소입니다."),
    ;

    private final Integer code;
    private final String message;

}
