package com.ll.topcastingbe.global.exception.cart;

import com.ll.topcastingbe.global.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartOptionNotExistException extends BusinessException {

    private static final String MESSAGE = CartErrorMessage.CART_PRODUCT_NOT_EXIST.getMessage();

    public CartOptionNotExistException() {
        super(MESSAGE);
    }

    public CartOptionNotExistException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
