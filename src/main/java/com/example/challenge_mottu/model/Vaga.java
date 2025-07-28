package com.example.challenge_mottu.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_vaga", uniqueConstraints = {@UniqueConstraint(columnNames = {"secao_id", "numeroVaga"})})
@SequenceGenerator(name = "vaga", sequenceName = "SQ_USER_VAGA", allocationSize = 1)
@Data
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

}