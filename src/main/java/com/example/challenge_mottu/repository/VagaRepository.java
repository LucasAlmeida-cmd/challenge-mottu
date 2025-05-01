package com.example.challenge_mottu.repository;

import com.example.challenge_mottu.model.Secao;
import com.example.challenge_mottu.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
    Vaga findByNumeroVaga(Integer num);
    boolean existsBySecaoAndNumeroVaga(Secao secao, int i);
    @Query("SELECT v FROM Vaga v " +
            "JOIN FETCH v.secao s " +
            "JOIN FETCH s.patio p " +
            "WHERE TRIM(UPPER(p.identificacao)) = TRIM(UPPER(:patioIdentificacao)) " +
            "AND TRIM(UPPER(s.identificacao)) = TRIM(UPPER(:secaoIdentificacao)) " +
            "AND v.numeroVaga = :numeroVaga")
    Optional<Vaga> findByPatioSecaoENumero(
            @Param("patioIdentificacao") String patioIdentificacao,
            @Param("secaoIdentificacao") String secaoIdentificacao,
            @Param("numeroVaga") Integer numeroVaga);

    @Query("SELECT s FROM Secao s WHERE s.identificacao = :nomeSecao AND s.patio.identificacao = :nomePatio")
    Optional<Secao> findByNomeAndNome(@Param("nomeSecao") String nomeSecao, @Param("nomePatio") String nomePatio);

    @Query("SELECT v FROM Vaga v WHERE v.numeroVaga = :identVaga " +
            "AND v.secao.identificacao = :identSecao " +
            "AND v.secao.patio.identificacao = :identPatio")
    Optional<Vaga> findByIdentificadores(String identVaga, String identSecao, String identPatio);

}
