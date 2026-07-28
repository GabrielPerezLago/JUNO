package com.gabriel.juno.infraestructure.centro.config;

import com.gabriel.juno.application.centro.usecase.CentroService;
import com.gabriel.juno.domain.port.centro.CentroRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CentroBingConfig {

    @Bean
    public CentroService centroService(CentroRepositoryPort centroRepositoryPort) {
        return new CentroService(centroRepositoryPort);
    }

}
