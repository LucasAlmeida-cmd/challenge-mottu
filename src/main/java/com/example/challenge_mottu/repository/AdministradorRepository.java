package com.example.challenge_mottu.repository;

import com.example.challenge_mottu.model.Administrador;
import com.example.challenge_mottu.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {


    List<Administrador> findByRole(Role role);

    Administrador findByCodigo(String codigo);
}
