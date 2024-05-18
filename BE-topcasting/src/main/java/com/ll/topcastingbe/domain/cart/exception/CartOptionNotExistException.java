package com.ll.topcastingbe.domain.cart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartOptionNotExistException extends RuntimeException {

    public CartOptionNotExistException() {
        super(CartErrorMessage.CART_PRODUCT_NOT_EXIST.getMessage());
    }

    public CartOptionNotExistException(String message) {
        super(message);
    }
}
