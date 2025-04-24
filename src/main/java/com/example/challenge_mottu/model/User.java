package com.example.challenge_mottu.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Calendar;

@MappedSuperclass
public abstract class User {

    @Column(name = "nome_usuario", nullable = false, length = 80)
    private String nomeUser;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_aniversario_usuario", nullable = false)
    private Calendar dataAniversario;

    @Column(name = "cpf_usuario", nullable = false, length = 11)
    private String cpfUser;

    @Column(name = "endereco_usuario", nullable = false)
    private String endereco;

    public User(String nomeUser, Calendar dataAniversario, String cpfUser, String endereco) {
        this.nomeUser = nomeUser;
        this.dataAniversario = dataAniversario;
        this.cpfUser = cpfUser;
        this.endereco = endereco;
    }

    public User() {

    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public Calendar getDataAniversario() {
        return dataAniversario;
    }

    public void setDataAniversario(Calendar dataAniversario) {
        this.dataAniversario = dataAniversario;
    }

    public String getCpfUser() {
        return cpfUser;
    }

    public void setCpfUser(String cpfUser) {
        this.cpfUser = cpfUser;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
