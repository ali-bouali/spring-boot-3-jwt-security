package com.herookie.employee.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
        super("The requested registration does not exist");
    }

    public EntityNotFoundException(String mensaje) {
        super(mensaje);
    }

}