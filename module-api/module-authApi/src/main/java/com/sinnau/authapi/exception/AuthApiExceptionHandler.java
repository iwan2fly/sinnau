package com.sinnau.authapi.exception;

import com.sinnau.common.model.CommonApiResponse;
import com.sinnau.common.exception.SinnauRuntimeException;
import com.sinnau.common.model.CommonError;
import com.sinnau.common.model.SinnauErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class AuthApiExceptionHandler {

    // 예외가 발생한 위치 확인
    private void loggingExceptionLocation(Exception e) {
        StackTraceElement element = e.getStackTrace()[0];
        String location = String.format("%s.%s(%s:%d)", element.getClassName(), element.getMethodName(),
                            element.getFileName(), element.getLineNumber());

        log.error("{}", location, e);
    }

    // 사용자 정의 예외 처리
    @ExceptionHandler(SinnauRuntimeException.class)
    public ResponseEntity<CommonApiResponse<?>> handleSinnauException(SinnauRuntimeException e) {

        loggingExceptionLocation(e);

        CommonError commonError = CommonError.builder()
                                .code(e.getCode())
                                .message(e.getMessage())
                                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommonApiResponse.error(HttpStatus.BAD_REQUEST.value(), commonError));
    }

    // IllegalArgumentException 처리 (입력값 검증 실패 등)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CommonApiResponse<?>> handleIllegalArgumentException(IllegalArgumentException e) {

        loggingExceptionLocation(e);

        CommonError commonError = CommonError.builder()
                                .code(SinnauErrorCode.UNDEFINED.getCode())
                                .message(e.getMessage())
                                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommonApiResponse.error(HttpStatus.BAD_REQUEST.value(), commonError));
    }

    // 기타 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonApiResponse<?>> handleAllExceptions(Exception e) {

        loggingExceptionLocation(e);

        CommonError commonError = CommonError.builder()
                                .code(SinnauErrorCode.UNDEFINED.getCode())
                                .message(e.getMessage())
                                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(CommonApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), commonError));

    }
}