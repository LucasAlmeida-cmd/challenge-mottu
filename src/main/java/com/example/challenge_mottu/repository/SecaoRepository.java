package com.example.challenge_mottu.repository;

import com.example.challenge_mottu.model.Patio;
import com.example.challenge_mottu.model.Secao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SecaoRepository extends JpaRepository<Secao, Long> {
    Secao findByIdentificacao(String ind);

    Secao findByIdentificacaoAndPatio(String s, Patio patio);

    @Query("SELECT s FROM Secao s WHERE s.identificacao = :nomeSecao AND s.patio.identificacao = :nomePatio")
    Optional<Secao> findByNomeAndNomePatio(@Param("nomeSecao") String nomeSecao, @Param("nomePatio") String nomePatio);


}
