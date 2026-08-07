package com.gabriel.juno.domain.models.usuario.exception;

public class UsuarioIsExistException extends UsuarioException {
    public UsuarioIsExistException(String usuario) {
        super("El usuario " + usuario + " ya existe");
    }
}
