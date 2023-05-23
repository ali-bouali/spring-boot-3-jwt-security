package com.herookie.employee.exceptions;

public class ErrorProcessingException extends RuntimeException {

    public ErrorProcessingException() {
        super("There was an unexpected mistake when trying to process your request ");
    }

    public ErrorProcessingException(String mensaje) {
        super(mensaje);
    }

    public ErrorProcessingException(String mensaje, Throwable root) {
        super(mensaje, root);
    }
}