package com.gabriel.juno.application.centro.usecase;

import com.gabriel.juno.domain.models.centro.Centro;
import com.gabriel.juno.domain.port.centro.CentroRepositoryPort;


import java.util.List;

public class CentroService {
    private final CentroRepositoryPort repository;

    public CentroService(CentroRepositoryPort repo) {
        this.repository = repo;
    }

    /**
     * @parmas {} ::
     *
     * Metdo para listar todos los centros
     *
     * @return List<Centro>
     * @author Gabriel
     * @application Juno
     */
    public List<Centro> listCentro() {
        return  repository.findByAll();
    }
}
