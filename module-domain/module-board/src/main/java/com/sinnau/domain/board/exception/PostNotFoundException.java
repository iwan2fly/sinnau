package com.sinnau.domain.board.exception;

import com.sinnau.common.exception.SinnauRuntimeException;
import com.sinnau.common.model.SinnauErrorCode;

public class PostNotFoundException extends SinnauRuntimeException {
    
    Integer code = SinnauErrorCode.POST_NOT_FOUND.getCode();

    public PostNotFoundException(){
        super(SinnauErrorCode.POST_NOT_FOUND.getMessage());
    }

    public PostNotFoundException(String message){
        super(message);
    }

    public PostNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}