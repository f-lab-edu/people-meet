package com.flabedu.peoplemeet.exception;

import com.flabedu.peoplemeet.exception.message.UserErrorMessage;

public class JWTValidateException extends ApplicationException {
    public JWTValidateException() {
        super(UserErrorMessage.EMAIL_NOT_FOUND.getMessage());
    }
}
