package com.ll.topcastingbe.global.exception.review;

import com.ll.topcastingbe.global.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateReviewException extends BusinessException {

    private static final String MESSAGE = ReviewErrorMessage.DUPLICATE_REVIEW.getMessage();

    public DuplicateReviewException() {
        super(MESSAGE);
    }

    public DuplicateReviewException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.CONFLICT.value();
    }
}
