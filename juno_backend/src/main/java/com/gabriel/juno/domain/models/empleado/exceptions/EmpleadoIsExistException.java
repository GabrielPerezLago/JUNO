package com.gabriel.juno.domain.models.empleado.exceptions;

public class EmpleadoIsExistException extends RuntimeException {
    public EmpleadoIsExistException(String message) {
        super("El empleado " + message + " ya existe");
    }
}
