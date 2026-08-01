package com.gabriel.juno.infraestructure.out.persistance.repositories.usuario;

import com.gabriel.juno.infraestructure.out.persistance.entities.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> { }
