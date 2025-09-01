package com.example.challenge_mottu.config;

import com.example.challenge_mottu.model.Administrador;
import com.example.challenge_mottu.model.Role;
import com.example.challenge_mottu.model.User;
import com.example.challenge_mottu.repository.AdministradorRepository;
import com.example.challenge_mottu.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initAdmin(AdministradorRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            List<Administrador> admins = userRepository.findByRole(Role.ADMIN);

            if (admins == null || admins.isEmpty()) {
                Administrador admin = new Administrador();
                admin.setNomeUser("admin");
                admin.setDataAniversario(LocalDate.of(2000, 1, 1));
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setEmail("admin");

                userRepository.save(admin);
                System.out.println("✅ Admin criado: login=admin, senha=admin");
            } else {
                System.out.println("ℹ️ Admin já existe no sistema");
                System.out.println("✅ Admin criado: login=admin, senha=admin");
            }
        };
    }
}