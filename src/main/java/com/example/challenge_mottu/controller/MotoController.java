package com.example.challenge_mottu.controller;

import com.example.challenge_mottu.model.Moto;
import com.example.challenge_mottu.model.StatusMoto;
import com.example.challenge_mottu.records_DTOs.MotoRecord;
import com.example.challenge_mottu.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/moto")
public class MotoController {

    @Autowired
    MotoService motoService;

    @GetMapping ("/novo")
    public String rotaPagina(Model model){
        model.addAttribute("moto", new MotoRecord("",0, "", StatusMoto.BRANCO,"",0,"",""));
        return "moto/formulario-moto";
    }

    @PostMapping
    public String adicionar(@Valid MotoRecord moto){
        motoService.cadastrar(moto);
        return "redirect:/moto";
    }


    @GetMapping
    public String listarTodas(Model model){
        model.addAttribute("motos", motoService.listarTodas());
        return "moto/listar";
    }

    @PutMapping
    @RequestMapping("/{chassi}")
    public ResponseEntity<Moto> atualizar(@PathVariable String chassi, @RequestBody Moto moto){
        return ResponseEntity.ok(motoService.atualizaPeloChassi(chassi,moto));
    }

    @DeleteMapping("/{chassi}")
    public ResponseEntity<Moto> deletarPorChassi(@PathVariable String chassi){
        motoService.remover(chassi);
        return ResponseEntity.noContent().build();
    }


}
