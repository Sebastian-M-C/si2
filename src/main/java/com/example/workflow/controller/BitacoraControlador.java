package com.example.workflow.controller;


import com.example.workflow.entity.Bitacora;
import com.example.workflow.repository.BitacoraRepositorio;
import io.jsonwebtoken.lang.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bitacoras")
public class BitacoraControlador {
    @Autowired
    public BitacoraRepositorio servicio;

    @GetMapping({"","/"})
    public String listarBitacora(Model modelo){
        var bitacoras=servicio.findAll();
        modelo.addAttribute("bitacora", bitacoras);
        return "bitacoras/index";
    }

//    @GetMapping({"","/"})
//    public List<Bitacora> listar(){
//        return Arrays.asList(
//                new Bitacora[1]
//        );
//    }

}
