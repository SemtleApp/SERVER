package com.semtleapp.semtleapp.global.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationCustomException extends AuthenticationException {

    public AuthenticationCustomException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthenticationCustomException(String msg) {
        super(msg);
    }

    public AuthenticationCustomException(ErrorCode errorCode) {
        super(errorCode.toString());
    }
}
