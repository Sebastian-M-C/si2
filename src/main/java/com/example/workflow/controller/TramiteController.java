package com.example.workflow.controller;


import com.example.workflow.entity.Tramite;
import com.example.workflow.service.TramiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TramiteController {

    @Autowired
    TramiteService tramiteService;

    @PostMapping("/saveTramite")
    public Tramite saveTramite(@RequestBody Tramite tramite){
        return tramiteService.saveTramite(tramite);
    }

    @PutMapping("/updateTramite/{id}")
    public Tramite updateLocal(@PathVariable Long id, @RequestBody Tramite tramite) {
        return tramiteService.updateTramite(id, tramite);
    }

    @DeleteMapping("/deleteTramite/{id}")
    public String deleteTramite(@PathVariable Long id) {
        tramiteService.deleteTramite(id);
        return "Registro eliminado con exito";
    }
}