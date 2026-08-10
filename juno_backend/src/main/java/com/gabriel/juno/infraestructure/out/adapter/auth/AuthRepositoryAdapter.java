package com.gabriel.juno.infraestructure.out.adapter.auth;

import com.gabriel.juno.domain.models.empleado.EmpleadoFullDTO;
import com.gabriel.juno.domain.models.empleado.exceptions.EmpleadoEstadoException;
import com.gabriel.juno.domain.models.empleado.exceptions.EmpleadoIsExistException;
import com.gabriel.juno.domain.models.empleado.exceptions.EmpleadoRolException;
import com.gabriel.juno.domain.models.token.TokenDataContainerDTO;
import com.gabriel.juno.domain.models.usuario.Usuario;
import com.gabriel.juno.domain.models.usuario.exception.UsuarioException;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
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
    public TokenDataContainerDTO login(String email, String password) {
        /* Auth de Spring */
        authManager.authenticate(new UsernamePasswordAuthenticationToken(
           email,
           password
        ));

        /* Comprobamos si el usuario existe */
        var usuarioEntity = usuarioRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsuarioNotExistException(email));

        /* Comprobamos si es un empleado */
        var empleadoEntity = empleadoRepository
                .findById(usuarioEntity.getId())
                .orElse(null);

        String token;
        String refreshToken;

        /*
            Generamos token dependiendo si es un usuario o un empleado
        */
        if (empleadoEntity == null) {
            token = jwtService.generateToken(usuarioEntity
                    .transferToSujetoDTO());
            refreshToken = jwtService.generateRefreshToken(usuarioEntity
                    .transferToSujetoDTO());

        } else {
            token = jwtService
                    .generateToken(empleadoEntity
                    .trasferToSujetoDTO());
            refreshToken = jwtService
                    .generateRefreshToken(empleadoEntity
                    .trasferToSujetoDTO());
        }

        /*
            Revocamos los tokens existentes
        */
        jwtService.revokeUserTokens(usuarioEntity
                .transferToUsuario());

        /*
            Guardamos los nuevos tokens
        */
        this.saveToken(token, usuarioEntity);
        this.saveRefreshToken(refreshToken, usuarioEntity);


        /*
            Retornamos los Tokens
         */
        return new TokenDataContainerDTO(token, refreshToken);

    }

    @Override
    public TokenDataContainerDTO loginByToken(String token) {
        return null;
    }

    @Override
    public TokenDataContainerDTO registerEmpleado(EmpleadoFullDTO empleadoFullDTO) {
        /* Comporbamos que el USUARIO NO EXISTA */
        UsuarioEntity existUsuario = usuarioRepository
                .findByEmail(empleadoFullDTO.email())
                .orElseThrow(null);

        if (existUsuario != null) throw new EmpleadoIsExistException(empleadoFullDTO.email());

        /*Guardamos el usuario en base de datos*/
        UsuarioEntity usuarioEntity = usuarioRepository.saveAndFlush(UsuarioEntity.builder()
                        .nombre(empleadoFullDTO.nombre())
                        .apellidos(empleadoFullDTO.apellidos())
                        .dni(empleadoFullDTO.dni())
                        .email(empleadoFullDTO.email())
                        .password(encoder.encode(empleadoFullDTO.password()))
                        .telefono(empleadoFullDTO.telefono())
                        .nacimiento(empleadoFullDTO.nacimiento())
                .build());

        if (usuarioEntity == null) throw new UsuarioException("No se ha podido dar de alta al usuario");

        /* Generamos Tokens */
        String token = jwtService
                .generateToken(usuarioEntity.transferToSujetoDTO());

        String refreshToken = jwtService
                .generateRefreshToken(usuarioEntity.transferToSujetoDTO());

        /* Guardamos Tokens en Base de datos */
        saveToken(token, usuarioEntity);
        saveRefreshToken(refreshToken, usuarioEntity);

        /* Damos de alta al EMPELAADO */
        EmpleadoEntity empleadoEnt = empleadoRepository.saveAndFlush(EmpleadoEntity.builder()
                        .estado(estadoEmpleadoRepository.findByEstado(empleadoFullDTO
                                .estado())
                                .orElseThrow(() -> new EmpleadoEstadoException()))
                        .rol(rolEmpleadoRepository.findByRol(empleadoFullDTO
                                .rol())
                                .orElseThrow(() -> new EmpleadoRolException()))
                        .usuario(usuarioEntity)
                .build());
        /* Retornamos el Token */
        return new TokenDataContainerDTO(token, refreshToken);

    }

    @Override
    public TokenDataContainerDTO registerUsuario(Usuario usuario) {
        /* Comporbamos que NO EXISTA el usuario */
        UsuarioEntity usuarioEntity = usuarioRepository
                .findByEmail(usuario.email())
                .orElse(null);

        if (usuarioEntity != null) throw new UsuarioIsExistException(usuario.email());

        /* Damso de alta al usuario */
        var usuarioEntitySave = usuarioRepository.saveAndFlush(UsuarioEntity.builder()
                .nombre(usuario.nombre())
                .apellidos(usuario.apellidos())
                .dni(usuario.dni())
                .email(usuario.email())
                .password(encoder.encode(usuario.password()))
                .telefono(usuario.telefono())
                .nacimiento(usuario.nacimiento())
                .build());

        if ( usuarioEntitySave == null ) throw new UsuarioException("El Usuario no ha podido ser dado de alta en estos momentos");

        /*Generamos tokens*/
        String token = jwtService.generateToken(usuarioEntitySave.transferToSujetoDTO());
        String refreshToken = jwtService.generateRefreshToken(usuarioEntitySave.transferToSujetoDTO());


        /*Gusrdamos el token en base de datos */
        saveToken(token , usuarioEntitySave);
        saveRefreshToken(refreshToken, usuarioEntitySave);

        /*Retornamos Tokens */
        return new TokenDataContainerDTO.builder()
                .token(token)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     *
     * @param token
     * @param usuario
     *
     * Metodo que guarda el token en base de datos asincronamente
     */
    @Async
    public void saveToken(String token, UsuarioEntity usuario) {
         TokenEntity tk = TokenEntity.builder()
                    .token(token)
                    .revoked(false)
                    .expired(false)
                    .usuario(usuario)
                    .build();
         tokenRepository.save(tk);
    }

    @Async
    public void saveRefreshToken(String token, UsuarioEntity usuario) {
        tokenRepository
                .save(TokenEntity.builder()
                        .token(token)
                        .revoked(false)
                        .expired(false)
                        .tokenType(null)
                        .usuario(usuario)
                        .build());
    }
}
