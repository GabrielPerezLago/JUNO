package com.gabriel.juno.infraestructure.centro.out.persistance.repository;

import com.gabriel.juno.infraestructure.centro.out.entity.CentroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CentroJpaRepository extends JpaRepository<CentroEntity, Long> { }
