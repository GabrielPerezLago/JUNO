package com.gabriel.juno.infraestructure.out.adapter.centro;

import com.gabriel.juno.domain.models.centro.Centro;
import com.gabriel.juno.domain.port.centro.CentroRepositoryPort;
import com.gabriel.juno.infraestructure.out.persistance.repositories.centro.CentroJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class CentroJpaRepositoryAdapter implements CentroRepositoryPort {
    private final CentroJpaRepository jpaRepository;

    @Override
    public List<Centro> findByAll() {
        return jpaRepository.findAll()
                .stream()
                .map(centro -> new Centro.builder()
                        .id(centro.getId())
                        .nombre(centro.getNombre())
                        .email(centro.getEmail())
                        .build())
                .toList();
    }


}
