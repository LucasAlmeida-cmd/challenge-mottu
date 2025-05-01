package com.example.challenge_mottu.repository;

import com.example.challenge_mottu.model.Patio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatioRepository extends JpaRepository<Patio, Long> {
    Patio findByIdentificacao(String identificacao);
}
