package com.gabriel.juno.infraestructure.out.persistance.repositories.empleado;

import com.gabriel.juno.infraestructure.out.persistance.entities.empleado.EmpleadoRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRolJpaRepository extends JpaRepository<EmpleadoRolEntity, Long> {}
