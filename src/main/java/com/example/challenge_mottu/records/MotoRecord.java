package com.example.challenge_mottu.records;

import com.example.challenge_mottu.model.StatusMoto;

public record MotoRecord(String modeloMoto,
                         int anoMoto,
                         String chassi,
                         StatusMoto status,
                         String motoqueiroCpf,
                         Integer vagaIdentificacao,
                         String patioIdentificacao,
                         String secaoIdentificacao) {
}
