package com.gabriel.juno.infraestructure.centro.config;

import com.gabriel.juno.application.centro.usecase.CentroService;
import com.gabriel.juno.domain.port.CentroRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CentroBingConfig {

    @Bean
    public CentroService centroService(CentroRepository centroRepository) {
        return new CentroService(centroRepository);
    }

}
