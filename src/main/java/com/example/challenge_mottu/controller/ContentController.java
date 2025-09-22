package com.example.challenge_mottu.controller;

import com.example.challenge_mottu.model.Motoqueiro;
import com.example.challenge_mottu.service.MotoqueiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class ContentController {

    @Autowired
    MotoqueiroService motoqueiroService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model, Principal principal) {
        String admin = principal.getName();
        model.addAttribute("nomeAdmin", admin);
        return "dashbord-admin";
    }

    @GetMapping("/motoqueiro/dashboard")
    public String dashboardMotoqueiro(Principal principal, Model model) {
        Optional<Motoqueiro> motoqueiro = motoqueiroService.buscarPorEmail(principal.getName());
        model.addAttribute("motoqueiro", motoqueiro);
        return "dashbord-motoqueiro";
    }



}
