package com.gabriel.juno.domain.models.empleado.exceptions;

public class EmpleadoRefusedException extends EmpleadoException {
    public EmpleadoRefusedException(String empleado) {
        super("El empleado " +  empleado + " no esta autorizado");
    }
}
