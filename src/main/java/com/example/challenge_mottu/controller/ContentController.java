package com.example.challenge_mottu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "dashbord-admin";
    }

    @GetMapping("/motoqueiro/dashboard")
    public String motoqueiroDashboard() {
        return "dashbord-motoqueiro";
    }



}
