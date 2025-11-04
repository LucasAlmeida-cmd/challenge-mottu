package com.example.challenge_mottu.controller;

import com.example.challenge_mottu.model.Patio;
import com.example.challenge_mottu.service.PatioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/patio")
public class PatioController {


    @Autowired
    PatioService patioService;

    @GetMapping("/novo")
    public String novoAdminForm(Model model) {
        model.addAttribute("patio", new Patio());
        return "patio/formulario-patio";
    }

    @PostMapping
    public String adicionar(Patio patio){
        patioService.adicionar(patio);
        return "redirect:/patio";
    }

    @GetMapping("/buscarPorIden")
    public String buscarPorIden(@RequestParam String identificacao, Model model) {
        Patio patio = patioService.buscarPorInd(identificacao);
        if (patio != null) {
            model.addAttribute("patios", List.of(patio));
        } else {
            model.addAttribute("patios", List.of());
            model.addAttribute("mensagem", "Pátio não encontrado");
        }
        return "patio/listar";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("patios", patioService.listarTodos());
        return "patio/listar";
    }

    @GetMapping("/editar/{indentificacao}")
    public String carregarFormularioEdicao(@PathVariable String indentificacao, Model model) {
        Patio patio = patioService.buscarPorInd(indentificacao);
        model.addAttribute("patio", patio);
        return "patio/formulario-atualizar-patio";
    }

    @PutMapping("/editar/{indentificacao}")
    public String atualizar(@PathVariable String indentificacao, @ModelAttribute Patio patio){
        System.out.println(patio.getIdentificacao());
        patioService.atualizarPatio(indentificacao, patio);
        return "redirect:/patio";
    }

    @DeleteMapping("/{indentificacao}")
    public String deletar(@PathVariable String indentificacao){
        patioService.remover(indentificacao);
        return "redirect:/patio";
    }

    // chamadas procedures:

    @GetMapping("/relatorio/json/{id}")
    public String relatorioVagasPatio(@PathVariable Long id, Model model) {
        String jsonResultado = patioService.chamarProcedureDeVagas(id);
        model.addAttribute("jsonResult", jsonResultado);
        model.addAttribute("patioId", id);
        return "admin/relatorio-vagas";
    }

    @GetMapping("/relatorio/texto")
    public String gerarRelatorioVagas(Model model) {
        String relatorioResultado = patioService.chamarPrGerarRelatorioVagas();
        model.addAttribute("relatorio", relatorioResultado);
        return "admin/relatorio-texto-vagas";
    }


    @GetMapping("/relatorio/validar-idade/{id}")
    public String relatorioValidaIdade(@PathVariable Long id, Model model) {
        String resultadoValidacao = patioService.chamarFnValidaIdade(id);
        model.addAttribute("userId", id);
        model.addAttribute("resultado", resultadoValidacao);
        return "admin/relatorio-idade";
    }




}
