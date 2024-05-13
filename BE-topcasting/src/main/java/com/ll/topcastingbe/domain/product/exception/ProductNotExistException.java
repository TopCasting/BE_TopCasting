package com.ll.topcastingbe.domain.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotExistException extends RuntimeException {

	public ProductNotExistException() {
		super(ProductErrorMessage.PRODUCT_NOT_EXIST.getMessage());
	}
	public ProductNotExistException(String message) {
		super(message);
	}
}
