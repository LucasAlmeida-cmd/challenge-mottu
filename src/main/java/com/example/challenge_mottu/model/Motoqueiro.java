package com.example.challenge_mottu.model;

import jakarta.persistence.*;

import java.util.Calendar;

@Entity
@Table(name = "tb_user_motoqueiro")
@SequenceGenerator(name = "motoqueiro", sequenceName = "SQ_USER_MOTOQUEIRO", allocationSize = 1)
public class Motoqueiro extends User{

    @Id
    @Column(name = "idMotoqueiro")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "motoqueiro")
    private Long id;

    @Column(name = "cnh_motoqueiro", nullable = false, length = 9)
    private String cnh;


    @OneToOne(mappedBy = "motoqueiro")
    private Moto moto;

    @Embedded
    private Endereco endereco;



    public Motoqueiro(String nomeUser, Calendar dataAniversario, String cpfUser, Endereco endereco, String cnh) {
        super(nomeUser, dataAniversario, cpfUser);
        this.endereco = endereco;
        this.cnh = cnh;
    }
    public Motoqueiro() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
