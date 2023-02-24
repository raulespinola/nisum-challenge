package org.nisum.challenge.exception;

public class CustomAuthenticateException extends RuntimeException{

    public CustomAuthenticateException(String message, Throwable cause) {
        super(message, cause);
    }
}
