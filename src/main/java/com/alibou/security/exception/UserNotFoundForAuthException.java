package com.alibou.security.exception;

public class UserNotFoundForAuthException extends RuntimeException {
    public UserNotFoundForAuthException(String message) {
        super(message);
    }
}
