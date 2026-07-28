package com.gabriel.juno.domain.port.centro;

import com.gabriel.juno.domain.models.centro.Centro;
import java.util.List;

/**
 * @implNote Dimain
 *  @apiNote  Puerto de los casos de uso de la entidad Centro
 */
public interface CentroRepositoryPort {
    List<Centro> findByAll();
}
