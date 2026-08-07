package com.gabriel.juno.infraestructure.security.jwt;


import com.gabriel.juno.domain.models.auth.SujetoDTO;
import com.gabriel.juno.domain.models.usuario.Usuario;
import com.gabriel.juno.domain.port.token.TokenComposerPort;
import com.gabriel.juno.infraestructure.out.persistance.entities.usuario.TokenEntity;
import com.gabriel.juno.infraestructure.out.persistance.repositories.usuario.TokenJpaRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.format.DateTimeFormatter;
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
    public Boolean validateToken(String token, Usuario usuario) {
        var username = extractUsernameToToken(token);
        return username.equals(usuario.email());

    }

    public String estractUserName(final String token) {
        return estractUserName(token);
    }

    @Override
    public void revokeUserTokens(Usuario usuario) {
        this.revokeAllUserTokens(usuario);
    }

    @Override
    protected String extractUsernameToToken(String token) {
        Claims jwtClaimToken = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return jwtClaimToken.getSubject();
    }

    @Override
    protected void revokeAllUserTokens(final Usuario usuario) {
        final List<TokenEntity> validUserTokens = tokenJpaRepository
                .findAllValidIsFalseOrRevoquedIsFaslseByUsuarioId(usuario.id())
                .stream().toList();

        if (!validUserTokens.isEmpty()) {
            for (TokenEntity token: validUserTokens) {
                token.setExpired(true);
                token.setRekoed(true);
            }
        }

        tokenJpaRepository.saveAll(validUserTokens);
    }

    @Override
    protected String tokenComposser(final SujetoDTO sujeto, final Long expiration) {
        return Jwts.builder()
                .id(sujeto.id().toString())
                .claims(Map.of(
                        "nombre", sujeto.nombre(),
                        "apellidos", sujeto.apellidos(),
                        "dni", sujeto.dni(),
                        "telefono", sujeto.telefono(),
                        "nacimiento", sujeto.nacimiento().format(DateTimeFormatter.ISO_LOCAL_DATE),
                        "rol", sujeto.rol().toString(),
                        "estado", sujeto.estado().toString(),
                        "id_centro", sujeto.idCentro(),
                        "id_aula", sujeto.idAula()
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
