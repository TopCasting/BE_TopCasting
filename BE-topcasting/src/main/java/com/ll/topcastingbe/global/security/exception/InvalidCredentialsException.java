package com.ll.topcastingbe.global.security.exception;

import com.ll.topcastingbe.global.exception.member.UserErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidCredentialsException extends AuthenticationException {

    private final static String MESSAGE = UserErrorMessage.INVALID_CREDENTIALS.getMessage();

    public InvalidCredentialsException(String message) {
        super(message);
    }

    public InvalidCredentialsException() {
        super(MESSAGE);
    }
}
