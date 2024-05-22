package com.ll.topcastingbe.global.exception;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final boolean result;
    private final String code;
    private final List<String> messages;

    @Builder
    public ErrorResponse(boolean result, String code, String message) {
        this.result = result;
        this.code = code;
        this.messages = new ArrayList<>();
        messages.add(message);
    }

    @Builder
    public ErrorResponse(boolean result, String code, List<String> messages) {
        this.result = result;
        this.code = code;
        this.messages = (messages == null) ? new ArrayList<>() : messages;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}
