package com.example.challenge_mottu.records;

import com.example.challenge_mottu.model.StatusMoto;
import jakarta.validation.constraints.NotBlank;

public record MotoRecord(@NotBlank(message = "Modelo não pode ficar em branco.")
                         String modeloMoto,
                         int anoMoto,
                         @NotBlank(message = "Chassi não pode ficar em branco.")
                         String chassi,
                         StatusMoto status,
                         @NotBlank(message = "CPF do Motoqueiro não pode ficar em branco.")
                         String motoqueiroCpf,
                         Integer vagaIdentificacao,
                         @NotBlank(message = "O nome do Pátio não pode ficar em branco.")
                         String patioIdentificacao,
                         @NotBlank(message = "O nome da seção não pode ficar em branco.")
                         String secaoIdentificacao) {
}
