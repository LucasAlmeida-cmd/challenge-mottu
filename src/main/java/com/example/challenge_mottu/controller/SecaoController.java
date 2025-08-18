package com.example.challenge_mottu.controller;

import com.example.challenge_mottu.model.Administrador;
import com.example.challenge_mottu.model.Secao;
import com.example.challenge_mottu.records_DTOs.SecaoRecord;
import com.example.challenge_mottu.service.PatioService;
import com.example.challenge_mottu.service.SecaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/secao")
public class SecaoController {

    @Autowired
    SecaoService secaoService;

    @Autowired
    PatioService patioService;


    @GetMapping("/novo")
    public String novoSecao(Model model){
        model.addAttribute("secao", new SecaoRecord("", ""));
        model.addAttribute("patios", patioService.listarTodos());
        return "secao/formulario-secao";
    }

    @PostMapping
    public String adicionar(@Valid SecaoRecord secao){
        secaoService.adicionar(secao);
        return "redirect:/secao";
    }

    @GetMapping
    public String buscarTodos(Model model){
        model.addAttribute("secoes", secaoService.listarTodos());
        return "secao/listar";
    }

    @GetMapping("/buscarSecaoPorNomeEPatio")
    public String buscarSecaoPorNomeEPatio(
            @RequestParam("identificacao") String nomeSecao,
            @RequestParam("identificacaoPatio") String nomePatio,
            Model model) {

        Secao secao = secaoService.buscarSecaoPorNomeEPatio(nomeSecao, nomePatio);
        model.addAttribute("secoes", List.of(secao));
        return "secao/listar";
    }

    @PutMapping("/{ind}")
    public ResponseEntity<Secao>atualizar(@PathVariable String ind, @RequestBody Secao secao){
        return ResponseEntity.ok(secaoService.atualizar(ind, secao));
    }


    @DeleteMapping("/{nomeSecao}/{nomePatio}")
    public String deletarSecaoPorNomeEPatioA(@PathVariable String nomeSecao,@PathVariable String nomePatio) {
        secaoService.deletarSecaoPorNomeEPatio(nomeSecao, nomePatio);
        return "redirect:/secao";
    }



}
