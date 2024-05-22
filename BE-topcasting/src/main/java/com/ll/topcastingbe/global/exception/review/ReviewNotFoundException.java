package com.ll.topcastingbe.global.exception.review;

import com.ll.topcastingbe.global.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewNotFoundException extends BusinessException {

    private static final String MESSAGE = ReviewErrorMessage.NOT_FOUND.getMessage();


    public ReviewNotFoundException() {
        super(MESSAGE);
    }

    public ReviewNotFoundException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
