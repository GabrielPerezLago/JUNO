package com.gabriel.juno.infraestructure.out.persistance.repositories.empleado;

import com.gabriel.juno.infraestructure.out.persistance.entities.empleado.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoJpaRepository extends JpaRepository<EmpleadoEntity, Long> {}
