package com.example.challenge_mottu.controller;

import com.example.challenge_mottu.model.Motoqueiro;
import com.example.challenge_mottu.service.MotoqueiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/motoqueiro")
public class MotoqueiroController {

    @Autowired
    MotoqueiroService service;

    @GetMapping
    public String listarTodos(Model model){
        model.addAttribute("motoqueiros", service.listarTodos());
        return "motoqueiro/listar";
    }

    @PostMapping
    public String adicionar(Motoqueiro motoqueiro){
        service.cadastrar(motoqueiro);
        return "redirect:/motoqueiro";
    }


    @GetMapping("/novo")
    public String novomMotoqueiroForm(Model model) {
        model.addAttribute("motoqueiro", new Motoqueiro());
        return "motoqueiro/formulario-motoqueiro";
    }

    @GetMapping("/editar/{cpf}")
    public String carregarFormularioEdicao(@PathVariable String cpf, Model model) {
        Motoqueiro motoqueiro = service.buscarPorCpf(cpf);
        model.addAttribute("motoqueiro", motoqueiro);
        return "motoqueiro/formulario-atualizar-motoqueiro";
    }

    @PutMapping("/editar/{cpf}")
    public String atualizar(@PathVariable String cpf, @ModelAttribute Motoqueiro motoqueiro){
        service.atualiza(cpf, motoqueiro);
        return "redirect:/motoqueiro";
    }

    @DeleteMapping("/{cpf}")
    public String deletarPeloCpf(@PathVariable String cpf){
        service.remover(cpf.replaceAll("[^0-9]", ""));
        return "redirect:/motoqueiro";
    }

    @GetMapping
    @RequestMapping("/buscarPorCpf")
    public String buscarPorCpf(@RequestParam String cpf, Model model){
        String cpfNumerico = cpf.replaceAll("[^0-9]", "");
        Motoqueiro motoqueiro = service.buscarPorCpf(cpfNumerico);
        if (motoqueiro != null) {
            model.addAttribute("motoqueiros", List.of(motoqueiro));
        } else {
            model.addAttribute("motoqueiros", List.of());
            model.addAttribute("mensagem", "motoqueiros não encontrado");
        }
        return "motoqueiro/listar";
    }
}
