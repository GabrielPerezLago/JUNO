package com.gabriel.juno.infraestructure.out.adapter.auth;

import com.gabriel.juno.domain.models.auth.AuthResponseMapper;
import com.gabriel.juno.domain.models.empleado.EmpleadoSecureDTO;
import com.gabriel.juno.domain.models.empleado.EmpleadoFullDTO;
import com.gabriel.juno.domain.models.empleado.exceptions.EmpleadoEstadoException;
import com.gabriel.juno.domain.models.empleado.exceptions.EmpleadoIsExistException;
import com.gabriel.juno.domain.models.empleado.exceptions.EmpleadoRolException;
import com.gabriel.juno.domain.models.usuario.Usuario;
import com.gabriel.juno.domain.models.usuario.UsuarioDTO;
import com.gabriel.juno.domain.models.usuario.exception.UsuarioIsExistException;
import com.gabriel.juno.domain.models.usuario.exception.UsuarioNotExistException;
import com.gabriel.juno.domain.port.auth.AuthRepositoryPort;
import com.gabriel.juno.infraestructure.out.persistance.entities.empleado.EmpleadoEntity;
import com.gabriel.juno.infraestructure.out.persistance.entities.usuario.TokenEntity;
import com.gabriel.juno.infraestructure.out.persistance.entities.usuario.UsuarioEntity;
import com.gabriel.juno.infraestructure.out.persistance.repositories.empleado.EmpleadoJpaRepository;
import com.gabriel.juno.infraestructure.out.persistance.repositories.empleado.EmpleadoRolJpaRepository;
import com.gabriel.juno.infraestructure.out.persistance.repositories.empleado.EstadoEmpleadoJpaRepository;
import com.gabriel.juno.infraestructure.out.persistance.repositories.usuario.TokenJpaRepository;
import com.gabriel.juno.infraestructure.out.persistance.repositories.usuario.UsuarioJpaRepository;
import com.gabriel.juno.infraestructure.security.jwt.JunoJwtTokenService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthRepositoryAdapter implements AuthRepositoryPort {
    private final PasswordEncoder encoder;
    private final JunoJwtTokenService jwtService;
    private final AuthenticationManager authManager;
    private final TokenJpaRepository tokenRepository;
    private final UsuarioJpaRepository usuarioRepository;
    private final EmpleadoJpaRepository empleadoRepository;
    private final EstadoEmpleadoJpaRepository estadoEmpleadoRepository;
    private final EmpleadoRolJpaRepository rolEmpleadoRepository;

    @Override
    public AuthResponseMapper login(String email, String password) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(
           email,
           password
        ));

        var usuarioEntity = usuarioRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsuarioNotExistException(email));

        var token = jwtService.generateToken(usuarioEntity
                .transferToUsuario());

        var refreshff


    }

    @Override
    public AuthResponseMapper loginByToken(String token) {

    }

    @Override
    public EmpleadoSecureDTO registerEmpleado(EmpleadoFullDTO empleadoFullDTO) {
        UsuarioEntity existUsuario = usuarioRepository
                .findByEmail(empleadoFullDTO.email())
                .orElse(null);
        if (existUsuario != null) throw new EmpleadoIsExistException(empleadoFullDTO.email());

        UsuarioEntity usuarioEnt = usuarioRepository.saveAndFlush(UsuarioEntity.builder()
                        .nombre(empleadoFullDTO.nombre())
                        .apellidos(empleadoFullDTO.apellidos())
                        .dni(empleadoFullDTO.dni())
                        .email(empleadoFullDTO.email())
                        .password(encoder.encode(empleadoFullDTO.password()))
                        .telefono(empleadoFullDTO.telefono())
                        .nacimiento(empleadoFullDTO.nacimiento())
                .build());

        String token = jwtService.generateToken(usuarioEnt.transferToUsuario());

        saveToken(token, usuarioEnt);

        EmpleadoEntity empleadoEnt = empleadoRepository.saveAndFlush(EmpleadoEntity.builder()
                        .estado(estadoEmpleadoRepository.findByEstado(empleadoFullDTO
                                .estado())
                                .orElseThrow(() -> new EmpleadoEstadoException()))
                        .rol(rolEmpleadoRepository.findByRol(empleadoFullDTO
                                .rol())
                                .orElseThrow(() -> new EmpleadoRolException()))
                        .usuario(usuarioEnt)
                .build());


        return empleadoEnt.transferToEmpleadoSecureDTO(token);

    }

    @Override
    public UsuarioDTO registerUsuario(Usuario usuario) {
        UsuarioEntity usuarioEntity = usuarioRepository
                .findByEmail(usuario.email())
                .orElse(null);


        if (usuarioEntity != null) throw new UsuarioIsExistException(usuario.email());

        String token = jwtService.generateToken(usuario);



        UsuarioEntity usuarioSave = usuarioRepository.saveAndFlush(UsuarioEntity.builder()
                .nombre(usuario.nombre())
                .apellidos(usuario.apellidos())
                .dni(usuario.dni())
                .email(usuario.email())
                .password(encoder.encode(usuario.password()))
                .telefono(usuario.telefono())
                .nacimiento(usuario.nacimiento())
                .build());

        /*Gusrdamos el token en base de datos */
        saveToken(token , usuarioEntity);


        return usuarioSave.transferToUsuarioDTO(token);
    }

    /**
     *
     * @param token
     * @param usuario
     *
     * Metodo que guarda el token en base de datos asincronamente
     */
    @Async
    public synchronized void saveToken(String token, UsuarioEntity usuario) {
         TokenEntity tk = TokenEntity.builder()
                    .token(token)
                    .rekoed(false)
                    .expired(false)
                    .usuario(usuario)
                    .build();
         tokenRepository.save(tk);
    }
}
