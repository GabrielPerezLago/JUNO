package com.gabriel.juno.infraestructure.out.persistance.entities.empleado;

import com.gabriel.juno.domain.models.empleados.utils.Estado;
import com.gabriel.juno.domain.models.empleados.utils.Rol;
import com.gabriel.juno.infraestructure.out.persistance.entities.aula.AulaEntity;
import com.gabriel.juno.infraestructure.out.persistance.entities.centro.CentroEntity;
import jakarta.persistence.*;
import jdk.jfr.MemoryAddress;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "juno", name = "usuario")
public class EmpleadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String apellidos;

    @Column(nullable = false)
    private String dni;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime nacimiento;

    @Column(nullable = false)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private EstadoEmpleadoEntity estado;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private EmpleadoRolEntity rol;

    @ManyToOne
    @JoinColumn(name = "id_centro", nullable = false)
    private CentroEntity centro;

    @ManyToOne
    @JoinColumn(name = "id_aula")
    private AulaEntity aula;

}
