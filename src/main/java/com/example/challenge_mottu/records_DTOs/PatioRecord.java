package com.example.challenge_mottu.records_DTOs;

import java.util.List;

public record PatioRecord(Long idPatio,
                          String identificacao,
                          double largura,
                          double comprimento,
                          List<SecaoResumoRecord> secoes) {
}


