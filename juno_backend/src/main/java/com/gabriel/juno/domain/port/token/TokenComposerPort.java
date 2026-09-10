package com.gabriel.juno.domain.port.token;

import com.gabriel.juno.domain.models.auth.SujetoDTO;
import com.gabriel.juno.domain.models.token.Token;
import com.gabriel.juno.domain.models.token.TokenDataContainerDTO;
import com.gabriel.juno.domain.models.usuario.Usuario;

import java.util.Date;

public abstract class TokenComposerPort {

    /**
     * @param sujeto
     *
     * Metodo que genera un token normal
     *
     * @return Token
     */
    public abstract String generateToken(final SujetoDTO sujeto);
    /**
     * Metodo que genera un token de refresco
     * @param sujeto
     * @return
     */
    public abstract String generateRefreshToken(final SujetoDTO sujeto);

    public abstract void revokeUserTokens(final Usuario usuario);

    /**
     * @param token
     * @param usuario
     * Metood que valida el token que el usuario a pasado
     * @return Boolean
     */
    public abstract Boolean validateTokenByUser(final String token, final Usuario usuario);


    /**
     * @param token
     * Metodo que extrae el username principal del token recibido
     * @return
     */
    protected abstract String extractUsernameToToken(String token);
    /**
     * @param sujeto
     * @param expiration
     *
     * Metodo que genera un token mediante un usuario y con un nivel de expiracion
     * @return String __ Token
     */
    protected abstract String tokenComposser(final SujetoDTO sujeto, final Long expiration);
    /**
     * @param usuario
     *
     * Metodo que desabilita el uso de todos los tokens del usuario
     */
    protected abstract void revokeAllUserTokens(final Usuario usuario);

    protected abstract Date extractExpiration(final String token);
}
