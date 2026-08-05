package com.gabriel.juno.domain.models.empleado.exceptions;

public class EmpleadoNotExistException extends RuntimeException {
    public EmpleadoNotExistException(String message) {
        super("El empleado " + message + " no existe");
    }
}
