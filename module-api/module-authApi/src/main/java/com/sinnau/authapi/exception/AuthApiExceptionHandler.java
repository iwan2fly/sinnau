package com.sinnau.authapi.exception;

import com.sinnau.common.api.model.CommonApiResponse;
import com.sinnau.common.exception.SinnauRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // 사용자 정의 예외 처리
    @ExceptionHandler(SinnauRuntimeException.class)
    public ResponseEntity<CommonApiResponse<?>> handleSinnauException(SinnauRuntimeException e) {
        log.error("SinnauRuntimeException: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommonApiResponse.error(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    // IllegalArgumentException 처리 (입력값 검증 실패 등)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CommonApiResponse<?>> handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("IllegalArgumentException: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommonApiResponse.error(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    // 기타 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonApiResponse<?>> handleAllExceptions(Exception e) {
        log.error("Unexpected error", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(CommonApiResponse.error(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(), 
                        "서버 내부 오류가 발생했습니다."));
    }
}