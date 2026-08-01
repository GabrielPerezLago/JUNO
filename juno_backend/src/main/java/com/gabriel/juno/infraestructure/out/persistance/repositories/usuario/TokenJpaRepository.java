package com.gabriel.juno.infraestructure.out.persistance.repositories.usuario;

import com.gabriel.juno.infraestructure.out.persistance.entities.usuario.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenJpaRepository extends JpaRepository<Token, Long> { }
