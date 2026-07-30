package com.gabriel.juno.infraestructure.out.persistance.repositories.empleado;

import com.gabriel.juno.infraestructure.out.persistance.entities.empleado.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoJpaRepository extends JpaRepository<EmpleadoEntity, Long> { }
