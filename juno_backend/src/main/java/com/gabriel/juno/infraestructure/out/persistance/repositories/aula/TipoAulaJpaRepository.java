package com.gabriel.juno.infraestructure.out.persistance.repositories.aula;

import com.gabriel.juno.infraestructure.out.persistance.entities.aula.TipoAulaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoAulaJpaRepository extends JpaRepository<TipoAulaEntity, Long> { }
