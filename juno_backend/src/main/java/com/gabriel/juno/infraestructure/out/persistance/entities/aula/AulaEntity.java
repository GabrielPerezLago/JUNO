package com.gabriel.juno.infraestructure.out.persistance.entities.aula;


import com.gabriel.juno.domain.models.aula.utils.TipoAula;
import com.gabriel.juno.infraestructure.out.persistance.entities.centro.CentroEntity;
import jakarta.persistence.*;
import jdk.jfr.MemoryAddress;
import lombok.*;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "juno", name = "aula")
public class AulaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_tipo")
    private TipoAulaEntity tipo;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_centro")
    private CentroEntity centro;


}
