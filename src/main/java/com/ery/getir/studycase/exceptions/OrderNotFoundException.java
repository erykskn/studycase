package com.ery.getir.studycase.exceptions;

import com.ery.getir.studycase.base.BaseException;

public class OrderNotFoundException extends BaseException {
    public OrderNotFoundException() {
        super("Order not found.");
    }
}
