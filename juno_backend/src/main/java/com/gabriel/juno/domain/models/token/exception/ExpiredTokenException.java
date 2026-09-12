package com.gabriel.juno.domain.models.token.exception;

public class ExpiredTokenException extends TokenException {
    public ExpiredTokenException() {
        super("El token ha caducado");
    }
}
