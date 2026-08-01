package com.gabriel.juno.infraestructure.out.adapter.auth;

import com.gabriel.juno.domain.models.auth.AuthResMapper;
import com.gabriel.juno.domain.models.empleado.EmpleadoDTO;
import com.gabriel.juno.domain.models.empleado.EmpleadoUserDTO;
import com.gabriel.juno.domain.models.usuario.Usuario;
import com.gabriel.juno.domain.models.usuario.UsuarioDTO;
import com.gabriel.juno.domain.port.auth.AuthRepositoryPort;
import com.gabriel.juno.infraestructure.out.persistance.repositories.empleado.EmpleadoJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthRepositoryAdapter implements AuthRepositoryPort {

    private final EmpleadoJpaRepository empleadoRepo;
    private final PasswordEncoder encoder;

    @Override
    public EmpleadoDTO loginEmpleado(String email, String password) {
        return null;
    }

    @Override
    public UsuarioDTO loginUsuario(String email, String password) {
        return null;
    }

    @Override
    public EmpleadoDTO registerEmpleado(EmpleadoUserDTO empleado) {
        return null;
    }

    @Override
    public UsuarioDTO registerUsuario(Usuario usuario) {
        return null;
    }
}
