package com.wangyousong.app.growthbackend.exception;

import lombok.Getter;

@Getter
public class StsTokenGenerateFailedException extends RuntimeException {
    private final String message;

    public StsTokenGenerateFailedException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
