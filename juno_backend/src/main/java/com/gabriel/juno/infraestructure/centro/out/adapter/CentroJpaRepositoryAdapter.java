package com.gabriel.juno.infraestructure.centro.out.adapter;

import com.gabriel.juno.domain.models.Centro;
import com.gabriel.juno.domain.port.CentroRepository;
import com.gabriel.juno.infraestructure.centro.out.persistance.repository.CentroJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class CentroJpaRepositoryAdapter implements CentroRepository {
    private final CentroJpaRepository jpaRepository;

    @Override
    public List<Centro> findByAll() {
        return jpaRepository.findAll()
                .stream()
                .map(centro -> new Centro.builder()
                        .id(centro.getId())
                        .nombre(centro.getNombre())
                        .email(centro.getEmail())
                        .fundacion(centro.getFundacion())
                        .build())
                .toList();
    }


}
