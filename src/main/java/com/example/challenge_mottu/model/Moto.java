package com.example.challenge_mottu.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_moto")
@SequenceGenerator(name = "moto", sequenceName = "SQ_USER_MOTO", allocationSize = 1)
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

    public Long getId() {
        return id;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getModeloMoto() {
        return modeloMoto;
    }

    public void setModeloMoto(String modeloMoto) {
        this.modeloMoto = modeloMoto;
    }

    public int getAnoMoto() {
        return anoMoto;
    }

    public void setAnoMoto(int anoMoto) {
        this.anoMoto = anoMoto;
    }

    public StatusMoto getStatus() {
        return status;
    }

    public void setStatus(StatusMoto status) {
        this.status = status;
    }

    public Motoqueiro getMotoqueiro() {
        return motoqueiro;
    }

    public void setMotoqueiro(Motoqueiro motoqueiro) {
        this.motoqueiro = motoqueiro;
    }
}
