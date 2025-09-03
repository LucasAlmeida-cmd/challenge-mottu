package com.example.challenge_mottu.controller;

import com.example.challenge_mottu.model.Administrador;
import com.example.challenge_mottu.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping("/buscarPorCodigo")
    public String atualizaPeloCpf(@RequestParam String codigo, Model model){
        Administrador admin = service.buscarPorCodigo(codigo);
        if (admin != null) {
            model.addAttribute("administradores", List.of(admin));
        } else {
            model.addAttribute("administradores", List.of());
            model.addAttribute("mensagem", "Administrador não encontrado");
        }
        return "admin/listar";
    }

    @GetMapping("/editar/{codigo}")
    public String carregarFormularioEdicao(@PathVariable String codigo, Model model) {
        Administrador administrador = service.buscarPorCodigo(codigo);
        model.addAttribute("administrador", administrador);
        return "admin/formulario-atualizar-admin";
    }

    @PutMapping("/editar/{codigo}")
    public String atualizar(@PathVariable String codigo, @ModelAttribute Administrador administrador) {
        service.atualizarAdminPorCpf(codigo, administrador);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deletarPorCpf(@PathVariable Long id) {
        service.removerAdmin(id);
        return "redirect:/admin";
    }



}
