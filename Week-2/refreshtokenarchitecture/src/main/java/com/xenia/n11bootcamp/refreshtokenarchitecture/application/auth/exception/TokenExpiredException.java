package com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.exception;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException(String message) {
        super(message);
    }
}
