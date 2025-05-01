package com.example.challenge_mottu.controller;

import com.example.challenge_mottu.model.Motoqueiro;
import com.example.challenge_mottu.service.MotoqueiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motoqueiro")
public class MotoqueiroController {

    @Autowired
    MotoqueiroService service;

    @PostMapping
    public ResponseEntity<Motoqueiro> adicionar(@RequestBody Motoqueiro motoqueiro){
        Motoqueiro motoqueiro1 = service.cadastrar(motoqueiro);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Motoqueiro>> listarTodos(){
        List<Motoqueiro> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @PutMapping
    @RequestMapping("/{cpf}")
    public ResponseEntity<Motoqueiro> atualizar(@PathVariable String cpf, @RequestBody Motoqueiro motoqueiro){
        String cpfNumerico = cpf.replaceAll("[^0-9]", "");
        return ResponseEntity.ok(service.atualiza(cpfNumerico, motoqueiro));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Motoqueiro> deletarPeloCpf(@PathVariable String cpf){
        service.remover(cpf);
        return ResponseEntity.noContent().build();
    }
}
