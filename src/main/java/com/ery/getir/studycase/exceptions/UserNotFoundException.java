package com.ery.getir.studycase.exceptions;

import com.ery.getir.studycase.base.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(){
        super("User not found.");
    }
}
