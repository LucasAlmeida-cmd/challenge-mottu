package com.example.challenge_mottu.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_moto")
@SequenceGenerator(name = "moto", sequenceName = "SQ_USER_MOTO", allocationSize = 1)
@Data
public class Moto {

    @Id
    @Column(name = "idMoto")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "moto")
    private Long id;

    @Column(name = "modelo_moto", nullable = false)
    private String modeloMoto;

    @Column(name = "ano_moto", nullable = false)
    private int anoMoto;

    @Column(name = "chassi_moto", nullable = false)
    private String chassi ;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_moto", nullable = false)
    private StatusMoto status;

    @OneToOne
    @JoinColumn(name = "idMotoqueiro")
    private Motoqueiro motoqueiro;


    @OneToOne
    @JoinColumn(name = "vaga_id")
    @JsonBackReference
    private Vaga vaga;


    public Moto(String modeloMoto, int anoMoto, String chassi, StatusMoto status, Motoqueiro motoqueiro,Vaga vaga) {
        this.modeloMoto = modeloMoto;
        this.anoMoto = anoMoto;
        this.chassi = chassi;
        this.status = status;
        this.motoqueiro = motoqueiro;
        this.vaga = vaga;
    }

    public Moto(){
    }

}
