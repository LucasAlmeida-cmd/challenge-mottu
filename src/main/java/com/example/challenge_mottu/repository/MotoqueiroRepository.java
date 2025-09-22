package com.example.challenge_mottu.repository;

import com.example.challenge_mottu.model.Motoqueiro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MotoqueiroRepository extends JpaRepository<Motoqueiro, Long> {
    Motoqueiro findByCpfUser(String cpf);
    Motoqueiro findMotoqueiroByCnh(String cnh);
    void deleteByCpfUser(String cpfUser);

    Optional<Motoqueiro> findByEmail(String email);
}
