package com.example.foodpick.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final int status;
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus().value();
        this.message = errorCode.getMessage();
    }
}
