package com.gabriel.juno.infraestructure.out.persistance.repositories.empleado;

import com.gabriel.juno.domain.models.empleado.utils.Estado;
import com.gabriel.juno.infraestructure.out.persistance.entities.empleado.EstadoEmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EstadoEmpleadoJpaRepository extends JpaRepository<EstadoEmpleadoEntity, Long> {
    public Optional<EstadoEmpleadoEntity> findByEstado(Estado estado);
}
