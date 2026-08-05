package com.gabriel.juno.infraestructure.out.adapter.auth;

import com.gabriel.juno.domain.models.empleado.EmpleadoSecureDTO;
import com.gabriel.juno.domain.models.empleado.EmpleadoFullDTO;
import com.gabriel.juno.domain.models.empleado.exceptions.EmpleadoIsExistException;
import com.gabriel.juno.domain.models.usuario.Usuario;
import com.gabriel.juno.domain.models.usuario.UsuarioDTO;
import com.gabriel.juno.domain.models.usuario.exception.UsuarioIsExistException;
import com.gabriel.juno.domain.port.auth.AuthRepositoryPort;
import com.gabriel.juno.infraestructure.out.persistance.entities.empleado.EmpleadoEntity;
import com.gabriel.juno.infraestructure.out.persistance.entities.usuario.TokenEntity;
import com.gabriel.juno.infraestructure.out.persistance.entities.usuario.UsuarioEntity;
import com.gabriel.juno.infraestructure.out.persistance.repositories.empleado.EmpleadoJpaRepository;
import com.gabriel.juno.infraestructure.out.persistance.repositories.empleado.EmpleadoRolJpaRepository;
import com.gabriel.juno.infraestructure.out.persistance.repositories.empleado.EstadoEmpleadoJpaRepository;
import com.gabriel.juno.infraestructure.out.persistance.repositories.usuario.TokenJpaRepository;
import com.gabriel.juno.infraestructure.out.persistance.repositories.usuario.UsuarioJpaRepository;
import com.gabriel.juno.infraestructure.out.persistance.tools.UsuarioEntityTransafer;
import com.gabriel.juno.infraestructure.security.jwt.JunoJwtTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthRepositoryAdapter implements AuthRepositoryPort {
    private final PasswordEncoder encoder;
    private final JunoJwtTokenService jwtService;
    private final TokenJpaRepository tokenRepository;
    private final UsuarioJpaRepository usuarioRepository;
    private final EmpleadoJpaRepository empleadoRepository;
    private final EstadoEmpleadoJpaRepository estadoEmpleadoRepository;
    private final EmpleadoRolJpaRepository rolEmpleadoRepository;

    @Override
    public EmpleadoSecureDTO loginEmpleado(String email, String password)  {

    }

    @Override
    public UsuarioDTO loginUsuario(String email, String password) {}

    @Override
    public EmpleadoSecureDTO registerEmpleado(EmpleadoFullDTO empleadoFullDTO) {
        UsuarioEntity existUsuario = usuarioRepository.findByEmail(empleadoFullDTO.email());
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

        String tocken = jwtService.generateToken(usuarioEnt.transferToUsuario());

        saveToken(tocken, usuarioEnt);

        EmpleadoEntity enmpleadoEnt = empleadoRepository.saveAndFlush(EmpleadoEntity.builder()
                        .estado(estadoEmpleadoRepository.findByEstado(empleadoFullDTO.estado()).get())
                        .usuario(usuarioEnt)
                .build());

        return new EmpleadoSecureDTO.builder().build();

    }

    @Override
    public UsuarioDTO registerUsuario(Usuario usuario) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByEmail(usuario.email());
        if (usuarioEntity != null) throw new UsuarioIsExistException(usuario.email());

        String token = jwtService.generateToken(usuario);



        UsuarioEntity usuarioSave = UsuarioEntity.builder()
                        .nombre(usuario.nombre())
                        .apellidos(usuario.apellidos())
                        .dni(usuario.dni())
                        .email(usuario.email())
                        .password(encoder.encode(usuario.password()))
                        .telefono(usuario.telefono())
                        .nacimiento(usuario.nacimiento())
                        .build();
        usuarioRepository.save(usuarioSave);

        /*Gusrdamos el token en base de datos */
        saveToken(token , usuarioEntity);


        return new UsuarioDTO(
                usuario.id(),
                usuario.nombre(),
                usuario.apellidos(),
                usuario.dni(),
                usuario.email(),
                usuario.telefono(),
                usuario.nacimiento()
        );
    }

    /**
     *
     * @param token
     * @param usuario
     *
     * Metodo que guarda con un hilo (Thread) el token en base de datos
     *
     * @hidden Thread
     */
    private synchronized void saveToken(String token, UsuarioEntity usuario) {
         TokenEntity tk = TokenEntity.builder()
                    .token(token)
                    .rekoed(false)
                    .expired(false)
                    .usuario(usuario)
                    .build();
         tokenRepository.save(tk);
    }
}
