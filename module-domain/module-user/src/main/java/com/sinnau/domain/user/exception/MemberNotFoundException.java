package com.sinnau.domain.user.exception;

import com.sinnau.common.exception.SinnauRuntimeException;
import com.sinnau.common.model.SinnauErrorCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberNotFoundException extends SinnauRuntimeException {

    Integer code        = SinnauErrorCode.MEMBER_NOT_FOUND.getCode();

    public MemberNotFoundException(){
        super( SinnauErrorCode.MEMBER_NOT_FOUND.getMessage() );
    }

    public MemberNotFoundException(String message){
        super( message );
    }

    public MemberNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
