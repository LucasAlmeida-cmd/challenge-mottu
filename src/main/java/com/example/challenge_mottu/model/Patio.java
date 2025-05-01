package com.example.challenge_mottu.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_patio")
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

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public Long getIdPatio() {
        return idPatio;
    }

    public void setIdPatio(Long idPatio) {
        this.idPatio = idPatio;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public List<Secao> getSecoes() {
        return secoes;
    }

    public void setSecoes(List<Secao> secoes) {
        this.secoes = secoes;
    }
}