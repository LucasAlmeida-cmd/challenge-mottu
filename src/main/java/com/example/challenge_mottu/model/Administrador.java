package com.example.challenge_mottu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tb_user_admin")
@DiscriminatorValue("ADMIN")
@PrimaryKeyJoinColumn(name = "id_admin", referencedColumnName = "id")
public class Administrador extends User{

    @Column(name = "codigo_admin", unique = true)
    private String codigo;

    public Administrador(String nomeUser, LocalDate dataAniversario, String password, Role role, String email, String codigo) {
        super(nomeUser, dataAniversario, password, role, email);
        this.setRole(Role.ADMIN);
        this.codigo = codigo;
    }

    public Administrador() {
        super();
        this.setRole(Role.ADMIN);
    }

}
