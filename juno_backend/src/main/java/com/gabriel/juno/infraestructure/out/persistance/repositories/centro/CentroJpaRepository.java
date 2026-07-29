package com.gabriel.juno.infraestructure.out.persistance.repositories.centro;

import com.gabriel.juno.infraestructure.out.persistance.entities.centro.CentroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CentroJpaRepository extends JpaRepository<CentroEntity, Long> { }
