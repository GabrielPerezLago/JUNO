package com.gabriel.juno.infraestructure.out.config.auth;

import com.gabriel.juno.application.auth.AuthService;
import com.gabriel.juno.domain.port.auth.AuthRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthBingConfig {

    @Bean
    public AuthService authService(AuthRepositoryPort authPort) {
        return new AuthService(authPort);
    }
}
