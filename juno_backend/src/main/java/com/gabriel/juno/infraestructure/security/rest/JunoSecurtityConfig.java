package com.gabriel.juno.infraestructure.security.rest;

import com.gabriel.juno.domain.models.token.exception.TokenException;
import com.gabriel.juno.infraestructure.out.persistance.repositories.usuario.TokenJpaRepository;
import com.gabriel.juno.infraestructure.security.filters.JwtAuthFilter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class JunoSecurtityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final TokenJpaRepository tokenJpaRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/juno/auth/**")
                                .permitAll() /* con esto permitimos todas las requestMarchesrs anteriroroes*/
                                /*Con esto decimos que las demas request tengan que estar auntenticadas */
                                .anyRequest()
                                .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(
                        logout ->
                                logout.logoutUrl("/juno/auth/logout")
                                        .addLogoutHandler(
                                                (request, response, authentication) -> {
                                                    final String authHeader = request.getHeader("Authorization");
                                                    logout(authHeader);
                                                })
                                        .logoutSuccessHandler(
                                                (request, response, authentication) ->
                                                        SecurityContextHolder.clearContext())
                );
        return http.build();
    }

    private void logout (String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Token invalido o no a sido enviado token");
        }

        final String jwtToken = token.substring(7);
        final var tk = tokenJpaRepository.findByToken(jwtToken)
                .orElseThrow(()-> new TokenException("El token no existe"));

        tk.setExpired(true);
        tk.setRevoked(true);

        tokenJpaRepository.save(tk);


    }

}
