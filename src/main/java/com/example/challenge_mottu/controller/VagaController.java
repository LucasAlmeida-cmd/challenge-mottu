package com.example.challenge_mottu.controller;


import com.example.challenge_mottu.model.Vaga;
import com.example.challenge_mottu.records_DTOs.VagaRecord;
import com.example.challenge_mottu.service.VagaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaga")
public class VagaController {


    @Autowired
    VagaService vagaService;


    @PostMapping
    public ResponseEntity<Vaga> adicionar(@RequestBody @Valid VagaRecord vaga){
        return ResponseEntity.ok(vagaService.adicionar(vaga));
    }

    @GetMapping
    public ResponseEntity<List<Vaga>> buscarTodas(){
        List<Vaga> vagas = vagaService.listarTodos();
        return ResponseEntity.ok(vagas);
    }

    @PutMapping
    @RequestMapping("/{num}")
    public ResponseEntity<Vaga> atualizar(@PathVariable Integer num, Vaga vaga){
        return ResponseEntity.ok(vagaService.atualizar(num, vaga));
    }

    @PutMapping("/{identVaga}/{identSecao}/{identPatio}")
    public ResponseEntity<Vaga> atualizarVagaPorIdentificadores(
            @PathVariable String identVaga,
            @PathVariable String identSecao,
            @PathVariable String identPatio,
            @RequestBody Vaga novaVaga) {
        Vaga vagaAtualizada = vagaService.atualizarVaga(identVaga, identSecao, identPatio, novaVaga);
        return ResponseEntity.ok(vagaAtualizada);
    }


    @DeleteMapping("/{num}")
    public ResponseEntity<Vaga> deletar(@PathVariable Integer num){
        vagaService.deletar(num);
        return ResponseEntity.ok().build();
    }

}
