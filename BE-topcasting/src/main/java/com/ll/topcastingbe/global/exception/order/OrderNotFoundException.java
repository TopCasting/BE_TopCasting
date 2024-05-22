package com.ll.topcastingbe.global.exception.order;

import com.ll.topcastingbe.global.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class OrderNotFoundException extends BusinessException {

    private final static String MESSAGE = OrderErrorMessage.ENTITY_NOT_FOUND.getMessage();

    public OrderNotFoundException() {
        super(MESSAGE);
    }

    public OrderNotFoundException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 0;
    }
}
