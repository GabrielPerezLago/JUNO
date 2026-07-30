package com.gabriel.juno.infraestructure.out.persistance.repositories.aula;

import com.gabriel.juno.infraestructure.out.persistance.entities.aula.AulaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AulaJpaRepository extends JpaRepository<AulaEntity, Long> { }
