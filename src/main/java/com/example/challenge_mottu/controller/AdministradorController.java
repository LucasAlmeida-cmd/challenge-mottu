package com.example.challenge_mottu.controller;

import com.example.challenge_mottu.model.Administrador;
import com.example.challenge_mottu.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdministradorController {

    @Autowired
    AdministradorService service;

    @GetMapping
    public String listarTodos(Model model){
        model.addAttribute("administradores", service.listarTodos());
        return "admin/listar";
    }

    @GetMapping("/novo")
    public String novoAdminForm(Model model) {
        model.addAttribute("administrador", new Administrador());
        return "admin/formulario-admin";
    }

    @PostMapping
    public String adicionarAdmin(Administrador administrador){
        service.adicionar(administrador);
        return "redirect:/admin" ;
    }

    @GetMapping
    @RequestMapping("/buscarPorCpf")
    public String atualizaPeloCpf(@RequestParam String cpf, Model model){
        String cpfNumerico = cpf.replaceAll("[^0-9]", "");
        Administrador admin = service.buscarPorCpf(cpfNumerico);
        if (admin != null) {
            model.addAttribute("administradores", List.of(admin)); // retorna só o encontrado
        } else {
            model.addAttribute("administradores", List.of()); // lista vazia
            model.addAttribute("mensagem", "Administrador não encontrado");
        }
        return "admin/listar";
    }

    @GetMapping("/editar/{cpf}")
    public String carregarFormularioEdicao(@PathVariable String cpf, Model model) {
        Administrador administrador = service.buscarPorCpf(cpf);
        model.addAttribute("administrador", administrador);
        return "admin/formulario-atualizar-admin";
    }

    @PutMapping("/editar/{cpf}")
    public String atualizar(@PathVariable String cpf, @ModelAttribute Administrador administrador) {
        service.atualizarAdminPorCpf(cpf, administrador);
        return "redirect:/admin";
    }

    @DeleteMapping("/{cpf}")
    public String deletarPorCpf(@PathVariable String cpf) {
        service.removerAdmin(cpf.replaceAll("[^0-9]", ""));
        return "redirect:/admin";
    }



}
