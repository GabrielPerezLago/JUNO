package com.gabriel.juno.infraestructure.out.persistance.entities.aula;

import com.gabriel.juno.domain.models.aula.utils.TipoAula;
import jakarta.persistence.*;
import lombok.*;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "juno", name = "tipo_aula")
public class TipoAulaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAula tipo;

    @Column(nullable = false)
    private Long ratio;
}
