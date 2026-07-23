package com.gabriel.juno.infraestructure.centro.out.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(schema = "juno", name = "centro")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CentroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String email;

    private LocalDateTime fundacion;
}
