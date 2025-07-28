package com.example.challenge_mottu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;

@Entity
@Table(name = "tb_user_motoqueiro")
@SequenceGenerator(name = "motoqueiro", sequenceName = "SQ_USER_MOTOQUEIRO", allocationSize = 1)
@Data
public class Motoqueiro extends User{

    @Id
    @Column(name = "idMotoqueiro")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "motoqueiro")
    private Long id;

    @Column(name = "cnh_motoqueiro", nullable = false, length = 9)
    private String cnh;


    @OneToOne(mappedBy = "motoqueiro")
    @JsonIgnore
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

}
