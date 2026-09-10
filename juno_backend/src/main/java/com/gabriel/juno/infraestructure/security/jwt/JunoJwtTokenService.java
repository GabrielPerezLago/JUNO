package com.gabriel.juno.infraestructure.security.jwt;


import com.gabriel.juno.domain.models.auth.SujetoDTO;
import com.gabriel.juno.domain.models.token.TokenDataContainerDTO;
import com.gabriel.juno.domain.models.token.exception.InvalidTokenException;
import com.gabriel.juno.domain.models.usuario.Usuario;
import com.gabriel.juno.domain.port.token.TokenComposerPort;
import com.gabriel.juno.infraestructure.out.persistance.entities.usuario.TokenEntity;
import com.gabriel.juno.infraestructure.out.persistance.repositories.usuario.TokenJpaRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Clase que se encarga de generar Json Web Tokens
 * @author Gabriel
 */
@Component
public class JunoJwtTokenService
        extends TokenComposerPort {

    private final TokenJpaRepository tokenJpaRepository;



    @Value("${api.security.jwt.secret}")
    private String key;

    @Value("${api.security.jwt.expiration}")
    private Long expiration;


    @Value("${api.security.jwt.regfresh-token.expiration}")
    private Long refreshTokenExpiration;

    @Value("${api.security.jwt.issuer}")
    private String issuer;

    public  JunoJwtTokenService(TokenJpaRepository tokenJpaRepository) {
        this.tokenJpaRepository = tokenJpaRepository;
    }



    @Override
    public String generateToken(final SujetoDTO sujeto) {
        return tokenComposser(sujeto, expiration);
    }


    @Override
    public String generateRefreshToken(final SujetoDTO sujeto) {
        return tokenComposser(sujeto, refreshTokenExpiration);
    }

    @Override
    public Boolean validateTokenByUser(String token, Usuario usuario) {
        var username = extractUsernameToToken(token);

        return username.equals(usuario.email()) && !isExpiredToken(token);

    }

    /**
     * @param token
     *
     * Metodo que devuelve el username (subject) del token
     *
     * @return {@link String}
     */
    public String estractUserName(final String token) {
        return extractUsernameToToken(token);
    }

    public  Boolean isExpiredToken(final String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * @param usuario
     * Metodo que desavilita los tokens del usuario como parametro
     */
    @Override
    public void revokeUserTokens(Usuario usuario) {
        this.revokeAllUserTokens(usuario);
    }


    @Override
    protected Date extractExpiration(String token) {
        Claims jwtToken = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return jwtToken.getExpiration();
    }

    /**
     * @param token
     * Metodo que extrae el username principal del token recibido
     * @return {@link String}
     */
    @Override
    protected String extractUsernameToToken(String token) {
        Claims jwtClaimToken = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return jwtClaimToken.getSubject();
    }

    /**
     * @param usuario
     *
     * Metodo que desabilita el uso de todos los tokens del usuario , los captura y si tiene tokens los desavilita TODOS
     */
    @Override
    protected void revokeAllUserTokens(final Usuario usuario) {
        final List<TokenEntity> validUserTokens = tokenJpaRepository
                .findAllExpiredIsFalseOrRevokedIsFalseByUsuarioId(usuario.id())
                .orElse(List.of());

        if (!validUserTokens.isEmpty()) {
            for (TokenEntity token: validUserTokens) {
                token.setExpired(true);
                token.setRevoked(true);
            }
        }

        tokenJpaRepository.saveAll(validUserTokens);
    }

    /**
     *
     * @param sujeto
     * @param expiration
     *
     * Metodo que genera un token mediante un usuario y con un nivel de expiracion
     * @return
     */
    @Override
    protected String tokenComposser(final SujetoDTO sujeto, final Long expiration) {
        return Jwts.builder()
                .id(sujeto.id().toString())
                .claims(Map.of(
                        "nombre", sujeto.nombre(),
                        "apellidos", sujeto.apellidos() == null ? "" : sujeto.apellidos(),
                        "dni", sujeto.dni(),
                        "telefono", sujeto.telefono(),
                        "nacimiento", sujeto.nacimiento() == null ? "" : sujeto.nacimiento(),
                        "rol", sujeto.rol() == null ? "" : sujeto.rol(),
                        "estado", sujeto.estado() == null ?  "" : sujeto.estado(),
                        "id_centro", sujeto.idCentro() == null ? "" : sujeto.idCentro(),
                        "id_aula", sujeto.idAula() == null ? "" : sujeto.idCentro()
                        ))
                .subject(sujeto.email())
                .issuer(issuer)
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(signKeyGenerator())
                .compact();


    }

    /**
     * Metodo que decodea la clave del token y lo conviente a una clave privada
     * @return
     */
    private SecretKey signKeyGenerator() {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private SecretKey getSignInKey() {
        byte[] keyByBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyByBytes);
    }







}
