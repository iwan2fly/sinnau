package com.sinnau.common.model;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class CommonError {
    private int errorCode;              // 에러코드
    private String message;             // 에러메시지
    private String debugInfo;           // 디버그 인포


}