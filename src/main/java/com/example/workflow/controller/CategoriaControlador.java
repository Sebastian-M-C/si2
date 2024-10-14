package com.example.workflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tramites")
public class CategoriaControlador {

    @GetMapping
    public String doc(){
        // Aquí debes devolver el nombre de la plantilla (sin la extensión .html)
        return "tramites/index";  // Asegúrate de que este archivo esté en la carpeta correcta
    }
}
