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

}
