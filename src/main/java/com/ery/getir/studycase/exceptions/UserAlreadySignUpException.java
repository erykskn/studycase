package com.ery.getir.studycase.exceptions;

import com.ery.getir.studycase.base.BaseException;

public class UserAlreadySignUpException extends BaseException {
    public UserAlreadySignUpException(){
        super("Email already is used.");
    }
}
