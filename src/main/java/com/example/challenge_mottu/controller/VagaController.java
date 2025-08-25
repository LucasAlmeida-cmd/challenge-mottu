package com.example.challenge_mottu.controller;


import com.example.challenge_mottu.model.Vaga;
import com.example.challenge_mottu.records_DTOs.VagaRecord;
import com.example.challenge_mottu.service.PatioService;
import com.example.challenge_mottu.service.SecaoService;
import com.example.challenge_mottu.service.VagaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vaga")
public class VagaController {


    @Autowired
    VagaService vagaService;

    @Autowired
    PatioService patioService;

    @Autowired
    SecaoService secaoService;

    @PostMapping("/novo")
    public String adicionar(@Valid @ModelAttribute("vaga")VagaRecord vaga){
        vagaService.adicionar(vaga);
        return "redirect:/vaga";
    }

    @GetMapping("/novo")
    public String novoSecao(Model model){
        model.addAttribute("vaga", new VagaRecord(0,false ,"",""));
        model.addAttribute("vagas", vagaService.listarTodos());
        model.addAttribute("patios", patioService.listarTodos());
        model.addAttribute("secoes", secaoService.listarTodos());
        return "vaga/formulario-vaga";
    }

    @GetMapping
    public String listarTodos(Model model){
        model.addAttribute("vagas", vagaService.listarTodos());
        return "vaga/listar";
    }

    @GetMapping("/buscaPersonalizada")
    public String buscarVagaPorNomeVagaIdenSecaoIdentPatio(
            @RequestParam String identVaga,
            @RequestParam String identSecao,
            @RequestParam String identPatio,
            Model model){
        System.out.println(identVaga);
        System.out.println(identSecao);
        System.out.println(identPatio);
        Vaga vaga = vagaService.buscaPersonalizada(identVaga, identSecao,identPatio);
        model.addAttribute("vagas", List.of(vaga));
        return "vaga/listar";
    }


    @PutMapping("/editar/{identVaga}/{identSecao}/{identPatio}")
    public String atualizarVagaPorIdentificadores(
            @PathVariable String identVaga,
            @PathVariable String identSecao,
            @PathVariable String identPatio,
            @ModelAttribute Vaga novaVaga) {
        vagaService.atualizarVaga(identVaga, identSecao, identPatio, novaVaga);
        return "redirect:/vaga";
    }

    @GetMapping("/editar/{identVaga}/{identSecao}/{identPatio}")
    public String carregarFormularioEdicao( @PathVariable String identVaga,
                                            @PathVariable String identSecao,
                                            @PathVariable String identPatio, Model model) {
        Vaga vaga = vagaService.buscaPersonalizada(identVaga, identSecao, identPatio);
        model.addAttribute("vaga", vaga);
        return "vaga/formulario-atualizar-vaga";
    }


    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {
        vagaService.deletar(id);
        return "redirect:/vaga";
    }

}
