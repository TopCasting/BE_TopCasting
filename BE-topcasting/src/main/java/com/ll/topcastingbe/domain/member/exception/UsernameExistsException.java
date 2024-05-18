package com.ll.topcastingbe.domain.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException(String message) {
        super(message);
    }

    public UsernameExistsException() {
        super(UserErrorMessage.USERNAME_ALREADY_EXISTS.getMessage());
    }
}
