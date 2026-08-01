package com.gabriel.juno.infraestructure.security.jwt;


import com.gabriel.juno.domain.models.usuario.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

/**
 * Clase que se encarga de generar Json Web Tokens
 * @author Gabriel
 */
public class JunoJwtTokenService {

    @Value("${api.security.jwt.secret}")
    private String key;

    @Value("${api.security.jwt.expiration}")
    private Long expiration;


    @Value("${api.security.jwt.regfresh-token.expiration}")
    private Long refreshTokenExpiration;

    @Value("${api.security.jwt.issuer}")
    private String issuer;

    /**
     * @param usuario
     *
     * Metodo que genera un token normal
     *
     * @return Token
     */
    public String generateToken(final Usuario usuario) {
        return tokenComposser(usuario, expiration);
    }

    /**
     * Metodo que genera un token de refresco
     * @param usuario
     * @return
     */
    public String genereateRefreshToken(final Usuario usuario) {
        return tokenComposser(usuario, refreshTokenExpiration);
    }

    /**
     * @param usuario
     * @param expiration
     *
     * Metodo que genera un token mediante un usuario y con un nivel de expiracion
     * @return String __ Token
     */
    private String tokenComposser(final Usuario usuario, final Long expiration) {
        return Jwts.builder()
                .id(usuario.id().toString())
                .claims(Map.of(
                        "nombre", usuario.nombre(),
                        "dni", usuario.dni()
                        ))
                .subject(usuario.email())
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








}
