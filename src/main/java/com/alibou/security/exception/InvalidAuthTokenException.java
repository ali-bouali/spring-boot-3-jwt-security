package com.alibou.security.exception;

public class InvalidAuthTokenException extends RuntimeException {
    public InvalidAuthTokenException(String message) {
        super(message);
    }
}
