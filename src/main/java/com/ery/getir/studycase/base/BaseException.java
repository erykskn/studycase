package com.ery.getir.studycase.base;

public class BaseException extends RuntimeException{
    public BaseException() {
        super("Base exception");
    }

    public BaseException(String message) {
        super(message);
    }
}
