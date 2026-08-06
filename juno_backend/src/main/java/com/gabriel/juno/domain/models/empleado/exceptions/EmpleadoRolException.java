package com.gabriel.juno.domain.models.empleado.exceptions;

public class EmpleadoRolException extends EmpleadoException{
    public EmpleadoRolException(){
        super("El rol del empleado no es valido");
    }

    public EmpleadoRolException(String rol) {
        super("El rol " + rol + " no es valido");
    }

}
