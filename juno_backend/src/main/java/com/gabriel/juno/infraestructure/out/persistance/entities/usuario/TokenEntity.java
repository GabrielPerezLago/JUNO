package com.gabriel.juno.infraestructure.out.persistance.entities.usuario;

import com.gabriel.juno.domain.models.token.tools.TokenType;
import jakarta.persistence.*;
import lombok.*;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "juno", name = "token")
public class TokenEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Boolean revoked;

    @Column(nullable = false)
    private Boolean expired;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TokenType tokenType = TokenType.BEARER;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;


    public boolean isRevoked() {
        return revoked;
    }

    public boolean isExpired() {
        return getExpired();
    }
}
