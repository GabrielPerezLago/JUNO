package com.gabriel.juno.domain.models.empleado.exceptions;

public class EmpleadoEstadoException extends EmpleadoException{
    public EmpleadoEstadoException() {
        super("El estado de empleado no es valido");
    }

    public EmpleadoEstadoException(String estado) {
        super("El estado " + estado + " no es valido");
    }
}
