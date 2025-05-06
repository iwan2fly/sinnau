package com.sinnau.common.exception;

import com.sinnau.common.model.SinnauErrorCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SinnauRuntimeException extends RuntimeException {

    Integer code        = SinnauErrorCode.UNDEFINED.getCode();

    public SinnauRuntimeException(){
        super( SinnauErrorCode.UNDEFINED.getMessage() );
    }

    public SinnauRuntimeException(String message){
        super( message );
    }  

    public SinnauRuntimeException(String message, Throwable cause){
        super(message, cause);
    }
}
