package com.gabriel.juno.infraestructure.out.persistance.repositories.usuario;

import com.gabriel.juno.infraestructure.out.persistance.entities.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.JpqlQueryBuilder;

import java.util.Optional;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {


    public Optional<UsuarioEntity> findByEmail(String email);

}
