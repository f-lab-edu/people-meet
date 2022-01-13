package com.flabedu.peoplemeet.exception;

import com.flabedu.peoplemeet.exception.message.UserErrorMessage;

public class EmailDuplicatedException extends ApplicationException {
    public EmailDuplicatedException() {super(UserErrorMessage.EMAIL_DUPLICATION.getMessage());}

    public EmailDuplicatedException(String message) {super(message);}
}
