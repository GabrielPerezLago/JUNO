package com.gabriel.juno.domain.models.empleados.exceptions;

public class EmpleadoIncorrectParamException extends RuntimeException {
    public EmpleadoIncorrectParamException(String message) {
        super("Error la insertar un atriburto en el Empleado: /n" + message);
    }
}
