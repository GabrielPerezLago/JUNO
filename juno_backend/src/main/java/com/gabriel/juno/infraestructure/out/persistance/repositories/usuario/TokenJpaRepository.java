package com.gabriel.juno.infraestructure.out.persistance.repositories.usuario;

import com.gabriel.juno.infraestructure.out.persistance.entities.usuario.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenJpaRepository extends JpaRepository<TokenEntity, Long> {

    Optional<TokenEntity> findByToken(String token);
    Optional<List<TokenEntity>> findAllExpiredIsFalseOrRevokedIsFalseByUsuarioId(Long id);
}
