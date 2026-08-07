package com.gabriel.juno.infraestructure.out.persistance.entities.usuario;

import jakarta.persistence.*;
import lombok.*;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "juno", name = "token")
public class TokenEntity {

    public enum TokenType {
        BEARER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Boolean rekoed;

    @Column(nullable = false)
    private Boolean expired;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType = TokenType.BEARER;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;


    public boolean isRevoqued() {
        return getRekoed();
    }

    public boolean isExpired() {
        return getExpired();
    }
}
