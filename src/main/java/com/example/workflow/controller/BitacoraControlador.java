package com.example.workflow.controller;

import com.example.workflow.repository.BitacoraRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bitacoras")
public class BitacoraControlador {
    @Autowired
    public BitacoraRepositorio servicio;

    @GetMapping({"","/listarbitacora"})
    public String listarBitacora(Model modelo){
        var bitacoras = servicio.findAll();
        modelo.addAttribute("bitacora", bitacoras);
        return "bitacoras/index";
    }
}
