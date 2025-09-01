package com.example.challenge_mottu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_user_motoqueiro")
@Getter
@Setter
@DiscriminatorValue("MOTOQUEIRO")
@PrimaryKeyJoinColumn(name = "id_motoqueiro", referencedColumnName = "id")
public class Motoqueiro extends User{

    @Column(name = "cnh_motoqueiro", nullable = false, length = 9)
    private String cnh;

    @OneToOne(mappedBy = "motoqueiro")
    @JsonIgnore
    private Moto moto;

    @Embedded
    private Endereco endereco;

    @Column(name = "cpf_usuario", nullable = false, length = 14, unique = true)
    private String cpfUser;

    public Motoqueiro(String nomeUser, LocalDate dataAniversario, String cpfUser, String password, Role role, String cnh, Moto moto, Endereco endereco, String email) {
        super(nomeUser, dataAniversario, password, role, email);
        this.cnh = cnh;
        this.moto = moto;
        this.endereco = endereco;
        setCpfUser(cpfUser);
    }

    public Motoqueiro() {
    }

    public String getCpfUserFormatado() {
        return cpfUser != null ?
                cpfUser.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4") :
                null;
    }

    public void setCpfUser(String cpf) {
            if (cpf == null) {
                throw new IllegalArgumentException("CPF não pode ser nulo");
            }
            String cpfNumerico = cpf.replaceAll("[^0-9]", "");
            if (!validarCpf(cpfNumerico)) {
                throw new IllegalArgumentException("CPF inválido");
            }
            this.cpfUser = cpfNumerico;
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
