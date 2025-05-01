package com.example.challenge_mottu.repository;

import com.example.challenge_mottu.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Administrador findByCpfUser(String cpf);
    void deleteAllByCpfUser(String cpf);
}
