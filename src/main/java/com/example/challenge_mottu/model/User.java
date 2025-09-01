package com.example.challenge_mottu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tb_user")
@Setter
@Getter
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_usuario", nullable = false, length = 80)
    private String nomeUser;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_aniversario_usuario", nullable = false)
    private LocalDate dataAniversario;


    @Column(name = "senha_usuario", nullable = false, length = 100)
    private String password;

    @Column(name = "email_usuario", nullable = false, length = 50)
    private String email;


    @Enumerated(EnumType.STRING)
    private Role role;



    public User(String nomeUser, LocalDate dataAniversario, String password, Role role, String email) {
        this.nomeUser = nomeUser;
        this.dataAniversario = dataAniversario;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public User() {

    }

}
