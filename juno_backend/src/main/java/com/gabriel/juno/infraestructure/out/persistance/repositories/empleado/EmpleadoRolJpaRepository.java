package com.gabriel.juno.infraestructure.out.persistance.repositories.empleado;

import com.gabriel.juno.domain.models.empleado.utils.Rol;
import com.gabriel.juno.infraestructure.out.persistance.entities.empleado.EmpleadoRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRolJpaRepository extends JpaRepository<EmpleadoRolEntity, Long> {
    public Optional<EmpleadoRolEntity> findByRol(Rol rol);
}
