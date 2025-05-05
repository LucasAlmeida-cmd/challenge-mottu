package com.example.challenge_mottu.records;

import jakarta.validation.constraints.NotBlank;

public record SecaoRecord(@NotBlank(message = "A secão precisa de uma identificação.")
                          String identificacao,
                          @NotBlank(message = "A seção precisa da identificação do Pátio.")
                          String identificacaoPatio) {
}
