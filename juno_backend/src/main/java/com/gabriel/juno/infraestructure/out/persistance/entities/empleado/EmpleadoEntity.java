package com.gabriel.juno.infraestructure.out.persistance.entities.empleado;

import com.gabriel.juno.domain.models.empleado.EmpleadoSecureDTO;
import com.gabriel.juno.infraestructure.out.persistance.entities.aula.AulaEntity;
import com.gabriel.juno.infraestructure.out.persistance.entities.centro.CentroEntity;
import com.gabriel.juno.infraestructure.out.persistance.entities.usuario.UsuarioEntity;
import jakarta.persistence.*;
import lombok.*;


@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "juno", name = "empleado")
public class EmpleadoEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;

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
    @JoinColumn(name = "id_aula", nullable = false)
    private AulaEntity aula;

    public EmpleadoSecureDTO transferToEmpleadoSecureDTO(String token) {
        return new EmpleadoSecureDTO.builder()
                .id(this.usuario.getId())
                .nombre(this.usuario.getNombre())
                .apellidos(this.usuario.getApellidos())
                .dni(this.usuario.getDni())
                .email(this.usuario.getEmail())
                .telefono(this.usuario.getTelefono())
                .nacimiento(this.usuario.getNacimiento())
                .estado(this.estado.getEstado())
                .rol(this.rol.getRol())
                .idAula(this.getAula().getId())
                .idCentro(this.getCentro().getId())
                .token(token)
                .build();
    }
}
