package com.gabriel.juno.infraestructure.out.adapter.auth;

import com.gabriel.juno.domain.models.auth.AuthModel;
import com.gabriel.juno.domain.port.auth.AuthRepositoryPort;
import com.gabriel.juno.infraestructure.out.persistance.repositories.empleado.EmpleadoJpaRepository;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthRepositoryAdapter implements AuthRepositoryPort {
    private final EmpleadoJpaRepository empleadoRepo;


    @Override
    public AuthModel login(String email, String password) {
        return null;
    }
}
