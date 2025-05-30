package com.sinnau.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SinnauErrorCode {

    UNDEFINED   ( 0, ""),

    MEMBER_NOT_FOUND( 2000, "회원이 존재하지 않습니다." ),
    ALREADY_REGISTERED_EMAIL( 2001, "이미 등록되어 있는 이메일 주소입니다."),

    BOARD_NOT_FOUND( 3000, "존재하지 않는 게시판입니다." ),
    POST_NOT_FOUND( 3001, "존재하지 않는 게시물입니다." ),
    ;

    private final Integer code;
    private final String message;

}
