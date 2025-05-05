package com.sinnau.common.model;

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
    private T data;          // 실제 데이터

    // 성공 응답 생성 메서드
    public static <T> CommonApiResponse<T> success(T data) {
        return new CommonApiResponse<>(HttpStatus.OK.value(), data);
    }

    // 실패 응답 생성 메서드
    public static <T> CommonApiResponse<T> error(int httpStatus, T data) {
        return new CommonApiResponse<>(httpStatus, data);
    }
}