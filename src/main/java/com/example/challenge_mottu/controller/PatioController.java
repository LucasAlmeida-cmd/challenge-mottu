package com.example.challenge_mottu.controller;

import com.example.challenge_mottu.model.Patio;
import com.example.challenge_mottu.records_DTOs.PatioRecord;
import com.example.challenge_mottu.service.PatioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patio")
public class PatioController {


    @Autowired
    PatioService patioService;

    @PostMapping
    public ResponseEntity<Patio> adicionar(@RequestBody Patio patio){
        patioService.adicionar(patio);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{ident}")
    public ResponseEntity<Patio>buscarPorIden(@PathVariable String ident) {
        return ResponseEntity.ok(patioService.buscarPorInd(ident));
    }

    @GetMapping
    public ResponseEntity<List<PatioRecord>> listarTodos() {
        return ResponseEntity.ok(patioService.listarTodos());
    }

    @PutMapping("/{indentificacao}")
    public ResponseEntity<Patio> atualizar(@PathVariable String indentificacao, @RequestBody Patio patio){
        return ResponseEntity.ok(patioService.atualizarPatio(indentificacao, patio));
    }

    @DeleteMapping("/{indentificacao}")
    public ResponseEntity<Patio> deletar(@PathVariable String indentificacao){
        patioService.remover(indentificacao);
        return ResponseEntity.ok().build();
    }





}
