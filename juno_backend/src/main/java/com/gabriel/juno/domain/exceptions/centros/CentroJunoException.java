package com.gabriel.juno.domain.exceptions.centros;

public class CentroJunoException extends RuntimeException {
    public CentroJunoException(String message) {
        super(" @Juno :--:  Error al dar de alta un centro : " + message);
    }
}
