package com.gabriel.juno.domain.models.usuario.exception;

public class UsuarioNotExist extends RuntimeException {
    public UsuarioNotExist(String msg) {
        super("El usuario --> " + msg + " <-- no existe");
    }
}
