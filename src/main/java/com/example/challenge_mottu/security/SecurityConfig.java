package com.example.challenge_mottu.security;

import com.example.challenge_mottu.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {


    @Autowired
    private final CustomUserDetailsService customUserDetailsService;
    @Autowired
    private CustomLoginSuccessHandler loginSuccessHandler;

    @Bean
    public UserDetailsService userDetailsService(){
        return customUserDetailsService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    // Filtros (Filters) - intercepta a requisicao e checa se e autenticada ou nao.
    // Cadeia de filtros de seguranca. HttpSecurity e o objeto para configurar a seguranca.
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(httpForm -> {
                    httpForm.loginPage("/login")
                            .usernameParameter("email")
                            .passwordParameter("password")
                            .successHandler(loginSuccessHandler)
                            .failureUrl("/login?error")
                            .permitAll();
                })

                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/", "/css/**", "/login", "/motoqueiro/recuperarSenha", "/motoqueiro/atualizarSenha").permitAll();
                    registry.requestMatchers("/motoqueiro/dashboard", "/motoqueiro/editar/fechada/**")
                            .hasRole("MOTOQUEIRO");
                    registry.requestMatchers("/moto/**", "/vaga/**", "/patio/**",
                            "/motoqueiro/**", "/secao/**", "/index", "/admin/**").hasAnyRole("ADMIN");


                    registry.anyRequest().authenticated();
                })
                .build();
    }



}
