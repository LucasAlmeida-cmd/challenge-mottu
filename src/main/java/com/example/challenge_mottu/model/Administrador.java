package com.example.challenge_mottu.model;

import jakarta.persistence.*;

import java.util.Calendar;

@Entity
@Table(name = "tb_user_admin")
@SequenceGenerator(name = "admin", sequenceName = "SQ_USER_ADMIN", allocationSize = 1)
public class Administrador extends User{

    @Id
    @Column(name = "idAdmin")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin")
    private Long id;


    public Administrador(String nomeUser, Calendar dataAniversario, String cpfUser) {
        super(nomeUser, dataAniversario, cpfUser);
    }

    public Administrador() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
