package com.example.workflow.controller;

import com.example.workflow.entity.Tramite;
import com.example.workflow.repository.TramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequisitosController {

    @Autowired
    public TramiteRepository tramiteRepository;

    @GetMapping("/tramites/iniciar/{id}")
    public String iniciarTramite(@PathVariable Long id, Model model) {
        Tramite tramite = tramiteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trámite no encontrado"));

        // Agregar los requisitos del trámite al modelo
        model.addAttribute("tramite", tramite);
        model.addAttribute("requisitos", tramite.getRequisitos());

        return "requisitos/index";  // Asegúrate de que esta ruta sea la correcta
    }

}
