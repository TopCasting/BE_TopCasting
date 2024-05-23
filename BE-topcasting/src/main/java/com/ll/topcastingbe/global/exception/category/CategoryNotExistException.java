package com.ll.topcastingbe.global.exception.category;

import com.ll.topcastingbe.global.exception.BusinessException;
import com.ll.topcastingbe.global.exception.cart.CartErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotExistException extends BusinessException {

    private static final String MESSAGE = CartErrorMessage.CART_PRODUCT_NOT_EXIST.getMessage();

    public CategoryNotExistException() {
        super(MESSAGE);
    }

    public CategoryNotExistException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
