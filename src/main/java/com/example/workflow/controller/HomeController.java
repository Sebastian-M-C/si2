package com.example.workflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")  // Define la ruta base
public class HomeController {

    @GetMapping  // Indica que este método responderá a solicitudes GET en la ruta "/"
    public String inicio() {
        return "index";  // Retorna el archivo index.html desde src/main/resources/templates
    }
}
