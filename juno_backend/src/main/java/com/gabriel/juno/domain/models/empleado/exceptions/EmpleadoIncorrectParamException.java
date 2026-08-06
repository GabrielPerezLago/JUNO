package com.gabriel.juno.domain.models.empleado.exceptions;

import com.gabriel.juno.infraestructure.out.persistance.entities.empleado.EmpleadoEntity;

public class EmpleadoIncorrectParamException extends EmpleadoException {
    public EmpleadoIncorrectParamException(String message) {
        super("Error la insertar un atriburto en el Empleado: /n" + message);
    }
}
