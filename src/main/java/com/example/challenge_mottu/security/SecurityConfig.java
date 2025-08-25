package com.example.challenge_mottu.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Filtros (Filters) - intercepta a requisicao e checa se e autenticada ou nao.
    // Cadeia de filtros de seguranca. HttpSecurity e o objeto para configurar a seguranca.
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        authorizeConfig -> {
                            //end-points
                            authorizeConfig.requestMatchers("/moto/**", "/vaga/**","/patio/**", "/motoqueiro/**",
                                    "/secao/**").permitAll();
                            //autorização styles
                            authorizeConfig.requestMatchers("/css/**").permitAll();
                            authorizeConfig.requestMatchers("/logout").permitAll();
                            authorizeConfig.anyRequest().authenticated();
                        })
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
