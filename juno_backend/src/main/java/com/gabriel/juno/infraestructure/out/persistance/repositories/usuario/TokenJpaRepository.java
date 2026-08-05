package com.gabriel.juno.infraestructure.out.persistance.repositories.usuario;

import com.gabriel.juno.domain.models.token.Token;
import com.gabriel.juno.infraestructure.out.persistance.entities.usuario.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

public interface TokenJpaRepository extends JpaRepository<TokenEntity, Long> { }
