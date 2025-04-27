package com.example.challenge_mottu.controller;

import com.example.challenge_mottu.model.Administrador;
import com.example.challenge_mottu.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdministradorController {

    @Autowired
    AdministradorService service;

    @PostMapping
    public ResponseEntity<Administrador> adicionarAdmin(@RequestBody Administrador administrador){
        Administrador administradorCriado = service.adicionar(administrador);
        return ResponseEntity.ok(administradorCriado);
    }

    @GetMapping
    public ResponseEntity<List<Administrador>> listarTodos(){
        List<Administrador> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @PutMapping
    @RequestMapping("/{cpf}")
    public ResponseEntity<?> atualizaPeloCpf(@PathVariable String cpf, @RequestBody Administrador administrador){
        String cpfNumerico = cpf.replaceAll("[^0-9]", "");
        return ResponseEntity.ok(service.atualizarAdminPorCpf(cpfNumerico,administrador));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarPorCpf(@PathVariable String cpf) {
        service.removerAdmin(cpf);
        return ResponseEntity.noContent().build();
    }



}
