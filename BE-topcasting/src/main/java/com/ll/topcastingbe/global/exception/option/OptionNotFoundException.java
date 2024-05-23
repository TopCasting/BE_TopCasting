package com.ll.topcastingbe.global.exception.option;

import com.ll.topcastingbe.global.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OptionNotFoundException extends BusinessException {

    private static final String MESSAGE = OptionErrorMessage.OPTION_NOT_FOUND.getMessage();

    public OptionNotFoundException() {
        super(MESSAGE);
    }

    public OptionNotFoundException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
