package com.flabedu.peoplemeet.exception;

import com.flabedu.peoplemeet.exception.message.UserErrorMessage;

public class EmailNotFoundException extends ApplicationException {
    public EmailNotFoundException() {super(UserErrorMessage.EMAIL_NOT_FOUND.getMessage());}

    public EmailNotFoundException(String message) {super(message);}
}
