package com.gabriel.juno.infraestructure.out.persistance.entities.usuario;

import com.gabriel.juno.domain.models.usuario.Usuario;
import com.gabriel.juno.domain.models.usuario.UsuarioDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "juno", name = "usuario")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String apellidos;

    @Column(unique = true, nullable = false)
    private String dni;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String telefono;
    private LocalDateTime nacimiento;


    public Usuario transferToUsuario() {
        return new Usuario.builder()
                .id(this.getId())
                .nombre(this.getNombre())
                .dni(this.getDni())
                .email(this.getEmail())
                .password(this.getPassword())
                .telefono(this.getTelefono())
                .nacimiento(this.getNacimiento())
                .build();
    }

    public UsuarioDTO transferToUsuarioDTO() {
        return new UsuarioDTO.builder()
                .id(this.getId())
                .nombre(this.getNombre())
                .dni(this.getDni())
                .email(this.getEmail())
                .telefono(this.getTelefono())
                .nacimiento(this.getNacimiento())
                .build();
    }

    public UsuarioDTO transferToUsuarioDTO(String token) {
        return new UsuarioDTO.builder()
                .id(this.getId())
                .nombre(this.getNombre())
                .dni(this.getDni())
                .email(this.getEmail())
                .telefono(this.getTelefono())
                .nacimiento(this.getNacimiento())
                .token(token)
                .build();
    }
}
