package com.ery.getir.studycase.exceptions;

import com.ery.getir.studycase.base.BaseException;

public class StockNotFounByGivenBookIdException extends BaseException {
    public StockNotFounByGivenBookIdException() {
        super("Stock not found by given book id.");
    }
}
