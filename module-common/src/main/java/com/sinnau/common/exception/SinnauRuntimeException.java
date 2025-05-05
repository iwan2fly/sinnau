package com.sinnau.common.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SinnauRuntimeException extends RuntimeException{
    public SinnauRuntimeException(String message){
        super(message);
    }  

    public SinnauRuntimeException(String message, Throwable cause){
        super(message, cause);
    }
}
