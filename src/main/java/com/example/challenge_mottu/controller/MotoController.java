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

    @GetMapping
    @RequestMapping("/buscarPorChassi")
    public String buscarPorChassi(@RequestParam String chassi, Model model){
        Moto moto = motoService.buscarPorChassi(chassi);
        if (moto != null) {
            model.addAttribute("motos", List.of(moto));
        } else {
            model.addAttribute("motos", List.of());
            model.addAttribute("mensagem", "motoqueiros não encontrado");
        }
        return "moto/listar";
    }

    @GetMapping("/editar/{chassi}")
    public String carregarFormularioEdicao(@PathVariable String chassi, Model model) {
        System.out.println(chassi);
        Moto moto = motoService.buscarPorChassi(chassi);
        model.addAttribute("moto", moto);
        return "moto/formulario-atualizar-moto";
    }

    @PutMapping("/editar/{chassi}")
    public String atualizar(@PathVariable String chassi, @ModelAttribute Moto moto){
        motoService.atualizaPeloChassi(chassi, moto);
        return "redirect:/moto";
    }

    @DeleteMapping("/{chassi}")
    public String deletarPorChassi(@PathVariable String chassi){
        System.out.println(chassi);
        motoService.remover(chassi);
        return "redirect:/moto";
    }


}
