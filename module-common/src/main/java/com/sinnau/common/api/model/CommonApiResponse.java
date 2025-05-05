package com.sinnau.common.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonApiResponse<T> {
    private int status;      // HTTP 상태 코드
    private String message;
    private T data;          // 실제 데이터

    // 성공 응답 생성 메서드
    public static <T> CommonApiResponse<T> success(T data) {
        return new CommonApiResponse<>(HttpStatus.OK.value(), null, data);
    }

    // 실패 응답 생성 메서드
    public static CommonApiResponse<?> error(int httpStatus, String message) {
        return new CommonApiResponse<>(httpStatus, message, null);
    }
}