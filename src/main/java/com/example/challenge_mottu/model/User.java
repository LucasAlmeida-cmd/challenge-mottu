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

    @Column(name = "cpf_usuario", nullable = false, length = 14)
    private String cpfUser;

    public User(String nomeUser, Calendar dataAniversario, String cpfUser) {
        this.nomeUser = nomeUser;
        this.dataAniversario = dataAniversario;
        setCpfUser(cpfUser);
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
        return formatarCpf(this.cpfUser);
    }

    public void setCpfUser(String cpf) {
        if (!validarCpf(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }
        this.cpfUser = cpf.replaceAll("[^0-9]", "");
    }


    private static boolean validarCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) digito1 = 0;

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) digito2 = 0;

        return (cpf.charAt(9) - '0' == digito1) && (cpf.charAt(10) - '0' == digito2);
    }

    private static String formatarCpf(String cpf) {
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

}
