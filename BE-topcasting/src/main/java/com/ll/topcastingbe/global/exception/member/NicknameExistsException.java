package com.ll.topcastingbe.global.exception.member;

import com.ll.topcastingbe.global.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NicknameExistsException extends BusinessException {

    private static final String MESSAGE = UserErrorMessage.NICKNAME_ALREADY_EXISTS.getMessage();

    public NicknameExistsException() {
        super(MESSAGE);
    }

    public NicknameExistsException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.CONFLICT.value();
    }
}
