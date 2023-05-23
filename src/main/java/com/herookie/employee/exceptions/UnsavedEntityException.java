package com.herookie.employee.exceptions;

public class UnsavedEntityException extends RuntimeException {

    public UnsavedEntityException() {
        super("There was an unexpected error when trying to save the record");
    }

    public UnsavedEntityException(String mensaje) {
        super(mensaje);
    }

}