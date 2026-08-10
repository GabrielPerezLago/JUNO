package com.gabriel.juno.infraestructure.security.filters;

import com.gabriel.juno.infraestructure.out.persistance.entities.usuario.TokenEntity;
import com.gabriel.juno.infraestructure.out.persistance.entities.usuario.UsuarioEntity;
import com.gabriel.juno.infraestructure.out.persistance.repositories.usuario.TokenJpaRepository;
import com.gabriel.juno.infraestructure.out.persistance.repositories.usuario.UsuarioJpaRepository;
import com.gabriel.juno.infraestructure.security.jwt.JunoJwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JunoJwtTokenService junoJwtTokenService;
    private final UserDetailsService userDetailsService;
    private final TokenJpaRepository tokenJpaRepository;
    private final UsuarioJpaRepository usuarioJpaRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /*
            Esto comprueba de donde viene la peticion y si viene de auth pasas al siguiente filtro
        */
        if(request.getServletPath().contains("/auth")) {
            filterChain.doFilter(request, response); // Pasa al suiguiente filtro
            return;
        }


        final String authHeader = request.getHeader("Authorozation");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
        }

        final var jwtToken = authHeader.substring(7);
        final var usuarioEmail = junoJwtTokenService.estractUserName(jwtToken);

        if (usuarioEmail == null || SecurityContextHolder.getContext().getAuthentication() == null) {
            return;
        }

        final TokenEntity token = tokenJpaRepository.findByToken(jwtToken)
                .orElse(null);

        if (token == null || token.isRevoked() || token.isExpired()) {
            filterChain.doFilter(request, response);
            return;
        }

        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(usuarioEmail);
        final Optional<UsuarioEntity> usuarioEntity = usuarioJpaRepository.findByEmail(userDetails.getUsername());

        if (usuarioEntity.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        // Validar el token con isToken Valid

        final var authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        filterChain.doFilter(request, response);
    }
}
