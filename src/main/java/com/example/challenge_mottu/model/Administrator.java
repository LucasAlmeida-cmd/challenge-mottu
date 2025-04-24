package com.example.challenge_mottu.model;

import jakarta.persistence.*;

import java.util.Calendar;

@Entity
@Table(name = "tb_user_admin")
@SequenceGenerator(name = "admin", sequenceName = "SQ_USER_ADMIN", allocationSize = 1)
public class Administrator extends User{

    @Id
    @Column(name = "idAdmin")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin")
    private Long id;


    public Administrator(String nomeUser, Calendar dataAniversario, String cpfUser, String endereco) {
        super(nomeUser, dataAniversario, cpfUser, endereco);
    }

    public Administrator() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
