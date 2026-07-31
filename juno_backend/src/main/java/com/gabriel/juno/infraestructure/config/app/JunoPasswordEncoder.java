package com.gabriel.juno.infraestructure.config.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class JunoPasswordEncoder {

    @Bean
    public PasswordEncoder passEncoderProvider() {
        return new BCryptPasswordEncoder();
    }
}
