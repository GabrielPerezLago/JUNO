package com.gabriel.juno.infraestructure.out.persistance.repositories.empleado;

import com.gabriel.juno.infraestructure.out.persistance.entities.empleado.EstadoEmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoEmpleadoJpaRepository extends JpaRepository<EstadoEmpleadoEntity, Long> { }
