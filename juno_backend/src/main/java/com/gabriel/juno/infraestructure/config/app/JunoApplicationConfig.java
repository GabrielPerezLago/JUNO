package com.gabriel.juno.infraestructure.config.app;

import com.gabriel.juno.domain.models.usuario.exception.UsuarioNotExistException;
import com.gabriel.juno.infraestructure.out.persistance.entities.usuario.UsuarioEntity;
import com.gabriel.juno.infraestructure.out.persistance.repositories.usuario.UsuarioJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class JunoApplicationConfig {
    private final UsuarioJpaRepository usuarioJpaRepository;


    /**
     * Metodo que le da a spring lo que tiene que hacer para encontrar al usuario
     * Response a la pregunta : Como spring sabe como comprueba que el usuario exista en base de datos???
     * @return
     */
    @Bean
    public UserDetailsService userDetailService() {
        return username -> {
            final UsuarioEntity usuario = usuarioJpaRepository.findByEmail(username)
                    .orElseThrow(() -> new UsuarioNotExistException(username));

            return User.builder()
                    .username(usuario.getEmail())
                    .password(usuario.getPassword())
                    .build();
        };
    }

    /**
     * Mertodo con el que le decimos a spring como desencriptar la contraseña del usuario
     * @return
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailService());
        authProvider.setPasswordEncoder(passEncoderProvider());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passEncoderProvider() {
        return new BCryptPasswordEncoder();
    }
}
