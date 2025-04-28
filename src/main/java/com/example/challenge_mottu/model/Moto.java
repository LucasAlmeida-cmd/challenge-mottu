package com.example.challenge_mottu.model;

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

    @Column(name = "status_moto", nullable = false)
    private StatusMoto status;

    @OneToOne
    @JoinColumn(name = "idMotoqueiro")
    private Motoqueiro motoqueiro;

    public Moto(String modeloMoto, int anoMoto, StatusMoto status, Motoqueiro motoqueiro) {
        this.modeloMoto = modeloMoto;
        this.anoMoto = anoMoto;
        this.status = status;
        this.motoqueiro = motoqueiro;
    }

    public Moto(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
