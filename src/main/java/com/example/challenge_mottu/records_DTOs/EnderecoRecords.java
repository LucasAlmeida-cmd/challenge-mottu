package com.example.challenge_mottu.records_DTOs;

import jakarta.validation.constraints.NotBlank;

public record EnderecoRecords(@NotBlank(message = "CEP não pode ficar em branco.")
                              String cep,
                              String logradouro,
                              String complemento,
                              String bairro,
                              String localidade,
                              String uf){
}
