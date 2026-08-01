package com.gabriel.juno.infraestructure.out.adapter.auth;

import com.gabriel.juno.domain.models.auth.AuthResMapper;
import com.gabriel.juno.domain.models.empleado.EmpleadoDTO;
import com.gabriel.juno.domain.models.empleado.EmpleadoUserDTO;
import com.gabriel.juno.domain.models.usuario.Usuario;
import com.gabriel.juno.domain.models.usuario.UsuarioDTO;
import com.gabriel.juno.domain.port.auth.AuthRepositoryPort;
import com.gabriel.juno.infraestructure.out.persistance.entities.usuario.UsuarioEntity;
import com.gabriel.juno.infraestructure.out.persistance.repositories.empleado.EmpleadoJpaRepository;
import com.gabriel.juno.infraestructure.out.persistance.repositories.usuario.UsuarioJpaRepository;
import com.gabriel.juno.infraestructure.security.jwt.JunoJwtTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthRepositoryAdapter implements AuthRepositoryPort {
    private final JunoJwtTokenService jwtService;
    private final PasswordEncoder encoder;
    private final UsuarioJpaRepository usuarioRepository;
    private final EmpleadoJpaRepository empleadoRepo;

    @Override
    public EmpleadoDTO loginEmpleado(String email, String password) {
        try {
            UsuarioEntity usuario = usuarioRepository.findByEmail(email);
        } catch (Exception ex) {

        }
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
