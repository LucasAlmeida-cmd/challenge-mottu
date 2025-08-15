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
