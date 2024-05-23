package com.ll.topcastingbe.global.exception.member;

import com.ll.topcastingbe.global.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserAndWriterNotMatchException extends BusinessException {

    private final static String MESSAGE = UserErrorMessage.USER_AND_WRITER_NOT_MATCH.getMessage();

    public UserAndWriterNotMatchException() {
        super(MESSAGE);
    }

    public UserAndWriterNotMatchException(String msg) {
        super(msg);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.FORBIDDEN.value();
    }
}
