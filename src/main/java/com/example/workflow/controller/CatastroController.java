package com.example.workflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/catastro")
public class CatastroController {

    @GetMapping
    public String saludar(){
        return "catastro/index";
    }
}
