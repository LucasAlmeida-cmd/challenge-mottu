package com.example.challenge_mottu.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VagaRecord(@NotNull(message = "Informar o número da vaga.")
                         int numeroVaga,
                         boolean disponivel,
                         @NotBlank(message = "Informar o nome da Seção")
                         String secaoIdentificacao,
                         @NotBlank(message = "Informar o nome do Pátio")
                         String padioIdentificacao) {
}
