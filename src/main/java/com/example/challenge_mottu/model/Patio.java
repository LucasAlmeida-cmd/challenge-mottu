package com.example.challenge_mottu.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_patio")
@Data
public class Patio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPatio;

    @Column(nullable = false, unique = true)
    private String identificacao;

    private double largura;
    private double comprimento;

    @OneToMany(mappedBy = "patio", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Secao> secoes = new ArrayList<>();


    public Patio(double largura, double comprimento, List<Secao> secoes,String identificacao) {
        this.largura = largura;
        this.comprimento = comprimento;
        this.secoes = secoes;
        this.identificacao = identificacao;
    }

    public Patio(){}
}