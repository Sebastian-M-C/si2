package com.example.workflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PapelesController {

    @GetMapping("/requisitos/papeles")  // Esta es la ruta correcta para acceder a "papeles.html"
    public String papeles(){
        return "requisitos/papeles";  // Aquí especificas que la vista está en la carpeta "requisitos"
    }
}