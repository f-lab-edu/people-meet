package com.flabedu.peoplemeet.exception;

import com.flabedu.peoplemeet.exception.message.UserErrorMessage;

public class EmailDuplicatedException extends ApplicationException {
    public EmailDuplicatedException(String message) {
        super(message);
    }
}
