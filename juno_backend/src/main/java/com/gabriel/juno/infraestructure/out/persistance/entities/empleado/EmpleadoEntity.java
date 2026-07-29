package com.gabriel.juno.infraestructure.out.persistance.entities.empleado;

import com.gabriel.juno.domain.models.empleados.utils.Estado;
import com.gabriel.juno.domain.models.empleados.utils.Rol;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
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


}
