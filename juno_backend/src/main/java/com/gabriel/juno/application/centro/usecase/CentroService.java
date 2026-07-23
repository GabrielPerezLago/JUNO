package com.gabriel.juno.application.centro.usecase;

import com.gabriel.juno.domain.models.Centro;
import com.gabriel.juno.domain.port.CentroRepository;


import java.util.List;

public class CentroService {
    private final CentroRepository repository;

    public CentroService(CentroRepository repo) {
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
