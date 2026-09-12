package com.gabriel.juno.infraestructure.out.adapter.auth;

import com.gabriel.juno.domain.models.empleado.EmpleadoFullDTO;
import com.gabriel.juno.domain.models.empleado.exceptions.EmpleadoEstadoException;
import com.gabriel.juno.domain.models.empleado.exceptions.EmpleadoIsExistException;
import com.gabriel.juno.domain.models.empleado.exceptions.EmpleadoRolException;
import com.gabriel.juno.domain.models.token.TokenDataContainerDTO;
import com.gabriel.juno.domain.models.token.exception.ExpiredTokenException;
import com.gabriel.juno.domain.models.token.exception.InvalidTokenException;
import com.gabriel.juno.domain.models.token.tools.TokenType;
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

import java.io.StringReader;

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

    /**
     *
     * @param email
     * @param password
     * Metodo que loguea al uusuario dependiendo de si es un empeleado o un usuario enviando el Token Correspondiente
     * @return {@link TokenDataContainerDTO}
     */
    @Override
    public TokenDataContainerDTO login(String email, String password) {

        /* Comprobamos si el usuario existe */
        var usuarioEntity = usuarioRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsuarioNotExistException(email));
        /* Auth de Spring */
        authManager.authenticate(new UsernamePasswordAuthenticationToken(
           email,
           password
        ));

        var empleadoEntity = empleadoRepository
                .findById(usuarioEntity.getId())
                .orElse(null);

        String token;
        String refreshToken;

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

        jwtService.revokeUserTokens(usuarioEntity
                .transferToUsuario());
        this.saveToken(token, usuarioEntity);

        return new TokenDataContainerDTO(token, refreshToken);

    }

    @Override
    public TokenDataContainerDTO loginByToken(final String refreshToken) {
        if (refreshToken == null || !refreshToken.startsWith("Bearer ")) {
            throw new InvalidTokenException("El token enviado no es valido");
        }

        final String tokenData = refreshToken.substring(7);
        final String usuarioEmail = jwtService.estractUserName(tokenData);

        if (usuarioEmail == null) {
            throw new InvalidTokenException("El token no es valido");
        }

        final var usuario = usuarioRepository.findByEmail(usuarioEmail)
                .orElseThrow(() -> new UsuarioNotExistException("El usuario del token enviado no existe"));

        if (jwtService.isExpiredToken(tokenData)) {
            throw new ExpiredTokenException();
        }
        if(!jwtService.validateTokenByUser(tokenData, usuario.transferToUsuario())) {
            throw new  InvalidTokenException("El token esta expirado o no es valido");
        }

        final String newToken = jwtService.generateToken(usuario.transferToSujetoDTO());
        final String newRefreshToken = jwtService.generateRefreshToken(usuario.transferToSujetoDTO());
        jwtService.revokeUserTokens(usuario.transferToUsuario());
        saveToken(newToken, usuario);
        return new TokenDataContainerDTO.builder()
                .token(newToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    /**
     * @param empleadoFullDTO
     * Metodo que registra un empleado
     * @return {@link TokenDataContainerDTO}
     */
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

    /**
     * @param usuario
     * Metodo que registra un usuario
     * @return {@link TokenDataContainerDTO}
     */
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
                    .tokenType(TokenType.BEARER)
                    .build();
         tokenRepository.save(tk);
    }

}
