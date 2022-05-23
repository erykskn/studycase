package com.ery.getir.studycase.exceptions;

import com.ery.getir.studycase.base.BaseException;

public class InvalidOrderException extends BaseException {
    public InvalidOrderException() {
        super("Invalid order.");
    }

    public InvalidOrderException(String message) {
        super(message);
    }
}
