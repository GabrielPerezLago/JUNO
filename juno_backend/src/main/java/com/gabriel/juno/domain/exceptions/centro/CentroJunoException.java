package com.gabriel.juno.domain.exceptions.centro;

public class CentroJunoException extends RuntimeException {
    public CentroJunoException(String message) {
        super(" @Juno :--:  Error al dar de alta un centro : " + message);
    }
}
