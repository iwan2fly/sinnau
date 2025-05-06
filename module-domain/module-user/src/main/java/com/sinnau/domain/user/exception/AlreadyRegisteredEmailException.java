package com.sinnau.domain.user.exception;

import com.sinnau.common.exception.SinnauRuntimeException;
import com.sinnau.common.model.SinnauErrorCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AlreadyRegisteredEmailException extends SinnauRuntimeException {

    Integer code        = SinnauErrorCode.ALREADY_REGISTERED_EMAIL.getCode();

    public AlreadyRegisteredEmailException(){
        super( SinnauErrorCode.ALREADY_REGISTERED_EMAIL.getMessage() );
    }

    public AlreadyRegisteredEmailException(String message){
        super( message );
    }

    public AlreadyRegisteredEmailException(String message, Throwable cause){
        super(message, cause);
    }
}
