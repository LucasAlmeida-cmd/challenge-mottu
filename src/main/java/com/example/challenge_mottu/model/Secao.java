package com.example.challenge_mottu.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_secao", uniqueConstraints = {@UniqueConstraint(columnNames = {"patio_id", "identificacao"})})
@SequenceGenerator(name = "secao", sequenceName = "SQ_USER_SECAO", allocationSize = 1)
public class Secao {
    @Id
    @Column(name = "idSecao")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secao")
    private Long id;

    @Column(nullable = false)
    private String identificacao;


    @OneToMany(mappedBy = "secao", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Vaga> vagas = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patio_id")
    @JsonBackReference
    private Patio patio;


    public Secao(List<Vaga> vagas, Patio patio, String identificacao) {
        this.vagas = vagas;
        this.patio = patio;
        this.identificacao = identificacao;
    }

    public Secao(){}
    public int calculaVagasRestantes() {
        return (int) vagas.stream().filter(Vaga::isDisponivel).count();
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<Vaga> getVagas() {
        return vagas;
    }

    public void setVagas(List<Vaga> vagas) {
        this.vagas = vagas;
    }

    public Patio getPatio() {
        return patio;
    }

    public void setPatio(Patio patio) {
        this.patio = patio;
    }
}
