package com.example.challenge_mottu.controller;

import com.example.challenge_mottu.model.Secao;
import com.example.challenge_mottu.records_DTOs.SecaoRecord;
import com.example.challenge_mottu.service.SecaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secao")
public class SecaoController {

    @Autowired
    SecaoService secaoService;

    @PostMapping
    public ResponseEntity<Secao>adicionar(@RequestBody @Valid SecaoRecord secao){
        return ResponseEntity.ok(secaoService.adicionar(secao));
    }

    @GetMapping
    public ResponseEntity<List<Secao>>buscarTodos(){
        return ResponseEntity.ok(secaoService.listarTodos());
    }

    @PutMapping("/{ind}")
    public ResponseEntity<Secao>atualizar(@PathVariable String ind, @RequestBody Secao secao){
        return ResponseEntity.ok(secaoService.atualizar(ind, secao));
    }


    @DeleteMapping("/{nomeSecao}/{nomePatio}")
    public ResponseEntity<Void> deletarSecaoPorNomeEPatio(@PathVariable String nomeSecao,@PathVariable String nomePatio) {
        secaoService.deletarSecaoPorNomeEPatio(nomeSecao, nomePatio);
        return ResponseEntity.ok().build();
    }



}
