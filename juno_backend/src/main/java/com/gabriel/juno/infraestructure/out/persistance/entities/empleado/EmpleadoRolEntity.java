package com.gabriel.juno.infraestructure.out.persistance.entities.empleado;

import com.gabriel.juno.domain.models.empleados.utils.Rol;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
@Builder
@Entity
@Table(schema = "juno", name = "empleado_rol")
public class EmpleadoRolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "nombre", nullable = false)
    private Rol rol;

}

