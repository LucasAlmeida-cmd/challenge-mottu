package com.example.challenge_mottu.repository;

import com.example.challenge_mottu.model.Moto;
import com.example.challenge_mottu.model.Motoqueiro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotoRepository extends JpaRepository<Moto, Long> {
    Moto findByChassi(String chassi);
    List<Moto> findByMotoqueiro(Motoqueiro motoqueiro);

}
