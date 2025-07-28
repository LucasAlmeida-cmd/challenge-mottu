package com.example.challenge_mottu.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_secao", uniqueConstraints = {@UniqueConstraint(columnNames = {"patio_id", "identificacao"})})
@SequenceGenerator(name = "secao", sequenceName = "SQ_USER_SECAO", allocationSize = 1)
@Data
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
}
