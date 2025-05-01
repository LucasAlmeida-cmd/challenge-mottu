package com.example.challenge_mottu.repository;

import com.example.challenge_mottu.model.Motoqueiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoqueiroRepository extends JpaRepository<Motoqueiro, Long> {
    Motoqueiro findByCpfUser(String cpf);
}
