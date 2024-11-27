package com.example.workflow.controller;

import com.example.workflow.entity.Bitacora;
import com.example.workflow.repository.BitacoraRepositorio;
import com.example.workflow.service.BitacoraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bitacoras")
public class BitacoraControlador {
    @Autowired
    public BitacoraRepositorio servicio;

//    @Autowired
//    public BitacoraServicio servicio;

    @GetMapping({"","/listarbitacora"})
    public String listarBitacora(Model modelo){
        var bitacoras = servicio.findAll();
        modelo.addAttribute("bitacora", bitacoras);
        return "bitacoras/index";
    }

//    @GetMapping({"", "/listarbitacora"})
//    public String listarBitacora(Model modelo) {
//        List<Bitacora> bitacoras = servicio.listarBitacora(); // Usa el método del servicio
//        modelo.addAttribute("bitacoras", bitacoras); // Verifica que el atributo sea "bitacora"
//        return "bitacoras/index";
//    }
}
