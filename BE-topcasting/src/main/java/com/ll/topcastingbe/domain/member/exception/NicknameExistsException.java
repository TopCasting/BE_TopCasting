package com.ll.topcastingbe.domain.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NicknameExistsException extends RuntimeException{

    public NicknameExistsException() {
        super(UserErrorMessage.NICKNAME_ALREADY_EXISTS.getMessage());
    }

    public NicknameExistsException(String message) {
        super(message);
    }
}
