package com.sinnau.common.model;

import lombok.*;

@Getter
@Builder
public class CommonError {

    private Integer code;               // 에러코드
    private String message;             // 에러메시지

    @JsonIgnore
    private String debugInfo;           // 디버그 인포

}