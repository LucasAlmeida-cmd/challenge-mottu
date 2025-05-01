package com.example.challenge_mottu.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_vaga", uniqueConstraints = {@UniqueConstraint(columnNames = {"secao_id", "numeroVaga"})})
@SequenceGenerator(name = "vaga", sequenceName = "SQ_USER_VAGA", allocationSize = 1)
public class Vaga {

    @Id
    @Column(name = "idVaga")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vaga")
    private Long id;

    @Column(nullable = false)
    private int numeroVaga;

    private boolean disponivel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "secao_id")
    @JsonBackReference
    private Secao secao;

    @OneToOne(mappedBy = "vaga", cascade = CascadeType.ALL, orphanRemoval = true)
    private Moto moto;

    public Vaga(int numeroVaga, boolean disponivel, Secao secao) {
        this.numeroVaga = numeroVaga;
        this.disponivel = disponivel;
        this.secao = secao;
    }

    public Vaga(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroVaga() {
        return numeroVaga;
    }

    public void setNumeroVaga(int numeroVaga) {
        this.numeroVaga = numeroVaga;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }


    public Secao getSecao() {
        return secao;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }
}