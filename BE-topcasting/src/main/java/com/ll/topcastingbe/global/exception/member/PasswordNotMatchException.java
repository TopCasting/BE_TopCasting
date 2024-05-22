package com.ll.topcastingbe.global.exception.member;

import com.ll.topcastingbe.global.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordNotMatchException extends BusinessException {

    private final static String MESSAGE = UserErrorMessage.PASSWORD_NOT_MATCH.getMessage();

    public PasswordNotMatchException() {
        super(MESSAGE);
    }

    public PasswordNotMatchException(String msg) {
        super(msg);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
