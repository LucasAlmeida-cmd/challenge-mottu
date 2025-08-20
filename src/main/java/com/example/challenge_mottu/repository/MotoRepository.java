package com.example.challenge_mottu.repository;

import com.example.challenge_mottu.model.Moto;
import com.example.challenge_mottu.model.Motoqueiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MotoRepository extends JpaRepository<Moto, Long> {
    Moto findByChassi(String chassi);
    List<Moto> findByMotoqueiro(Motoqueiro motoqueiro);

    @Query("SELECT m FROM Moto m " +
            "LEFT JOIN FETCH m.motoqueiro " +
            "LEFT JOIN FETCH m.vaga v " +
            "LEFT JOIN FETCH v.secao s " +
            "LEFT JOIN FETCH s.patio")
    List<Moto> findAllWithRelations();
}
