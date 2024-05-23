package com.ll.topcastingbe.global.exception.product;

import com.ll.topcastingbe.global.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotExistException extends BusinessException {

    private static final String MESSAGE = ProductErrorMessage.PRODUCT_NOT_EXIST.getMessage();

    public ProductNotExistException() {
        super(MESSAGE);
    }

    public ProductNotExistException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
