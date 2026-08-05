package com.gabriel.juno.domain.models.usuario.exception;

public class UsuarioNotExistException extends RuntimeException {
    public UsuarioNotExistException(String msg) {
        super("El usuario " + msg + " no existe");
    }
}
