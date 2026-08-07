package com.gabriel.juno.infraestructure.out.persistance.repositories.usuario;

import com.gabriel.juno.domain.models.token.Token;
import com.gabriel.juno.domain.models.usuario.Usuario;
import com.gabriel.juno.infraestructure.out.persistance.entities.usuario.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface TokenJpaRepository extends JpaRepository<TokenEntity, Long> {

    Optional<TokenEntity> findByToken(String token);
    Optional<TokenEntity> findAllValidIsFalseOrRevoquedIsFaslseByUsuarioId(Long id);
}
