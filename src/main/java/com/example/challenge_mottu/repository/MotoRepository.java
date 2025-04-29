package com.example.challenge_mottu.repository;

import com.example.challenge_mottu.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface MotoRepository extends JpaRepository<Moto, Long> {
    Moto findByChassi(String chassi);
}
