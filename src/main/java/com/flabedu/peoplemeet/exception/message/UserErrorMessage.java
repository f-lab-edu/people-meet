package com.flabedu.peoplemeet.exception.message;

public enum UserErrorMessage implements BaseErrorMessage {

    EMAIL_DUPLICATION("Email is Duplication"),
    EMAIL_NOT_FOUND("Email is Not Found"),
    ;

    private final String message;

    UserErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
