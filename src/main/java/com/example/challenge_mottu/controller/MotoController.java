package com.example.challenge_mottu.controller;

import com.example.challenge_mottu.model.Moto;
import com.example.challenge_mottu.records.MotoRecord;
import com.example.challenge_mottu.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moto")
public class MotoController {

    @Autowired
    MotoService motoService;

    @PostMapping
    public ResponseEntity<Moto> adicionar(@RequestBody @Valid MotoRecord moto){
        Moto moto1 = motoService.cadastrar(moto);
        return ResponseEntity.ok(moto1);
    }

    @GetMapping
    public ResponseEntity<List<Moto>> listarTodas(){
        List<Moto> motos = motoService.listarTodas();
        return ResponseEntity.ok(motos);
    }

    @PutMapping
    @RequestMapping("/{chassi}")
    public ResponseEntity<Moto> atualizar(@PathVariable String chassi, @RequestBody Moto moto){
        return ResponseEntity.ok(motoService.atualizaPeloChassi(chassi,moto));
    }

    @DeleteMapping("/{chassi}")
    public ResponseEntity<Moto> deletarPorChassi(@PathVariable String chassi){
        motoService.remover(chassi);
        return ResponseEntity.noContent().build();
    }


}
