package com.example.challenge_mottu.config;

import com.example.challenge_mottu.model.Administrador;
import com.example.challenge_mottu.model.Role;
import com.example.challenge_mottu.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("admin").isEmpty()) {
                Administrador admin = new Administrador();
                admin.setNomeUser("admin");
                admin.setDataAniversario(LocalDate.of(2000, 1, 1));
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setEmail("admin");
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
                System.out.println("✅ Admin salvo: " + admin.getEmail() + " / " + admin.getPassword());
            }
        };
    }

}