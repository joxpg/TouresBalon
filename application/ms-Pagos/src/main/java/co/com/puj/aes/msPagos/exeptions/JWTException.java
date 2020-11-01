package com.sw.ingenieria.simed.exeptions;

public class JWTException extends Exception {

    private static final long serialVersionUID = 2893141502868586192L;

    public JWTException(String message) {
        super(message);
    }

    public JWTException(String message, Throwable cause) {
        super(message, cause);
    }
}

