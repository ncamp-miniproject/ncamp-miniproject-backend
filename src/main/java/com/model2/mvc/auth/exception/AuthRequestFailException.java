package com.model2.mvc.auth.exception;

public class AuthRequestFailException extends Exception {

    public AuthRequestFailException() {
        super();
    }

    public AuthRequestFailException(String message) {
        super(message);
    }

    public AuthRequestFailException(Exception e) {
        super(e);
    }
}
