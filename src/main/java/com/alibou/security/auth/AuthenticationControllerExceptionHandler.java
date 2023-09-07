package com.alibou.security.auth;

import com.alibou.security.exception.InvalidAuthTokenException;
import com.alibou.security.exception.UserNotFoundForAuthException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
public class AuthenticationControllerExceptionHandler {
    @ExceptionHandler(InvalidAuthTokenException.class)
    public ResponseEntity<String> handleInvalidAuthTokenException(InvalidAuthTokenException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundForAuthException.class)
    public ResponseEntity<String> handleUserNotFoundForAuthException(UserNotFoundForAuthException ex, WebRequest request) {
        return ResponseEntity.status(UNAUTHORIZED).body(ex.getMessage());
    }
}
