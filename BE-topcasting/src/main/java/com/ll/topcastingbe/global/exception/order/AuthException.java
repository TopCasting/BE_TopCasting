package com.ll.topcastingbe.global.exception.order;

import com.ll.topcastingbe.global.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthException extends BusinessException {

    private final static String MESSAGE = OrderErrorMessage.UNAUTHORIZED_USER.getMessage();

    public AuthException() {
        super(MESSAGE);
    }

    public AuthException(String message) {
        super(message);
    }


    @Override
    public int getStatusCode() {
        return HttpStatus.FORBIDDEN.value();
    }
}
