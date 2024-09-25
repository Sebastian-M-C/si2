package com.example.workflow.controller;


import com.example.workflow.entity.Requisito;
import com.example.workflow.service.RequisitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequisitoController {
    @Autowired
    RequisitoService requisitoService;

    @PostMapping("/saveRequisito")
    public Requisito saveRequisito(@RequestBody Requisito requisito){
        return requisitoService.saveRequisito(requisito);
    }

    @GetMapping("/findAllRequisitos")
    public List<Requisito> findAllLocals() {
        return requisitoService.findAllRequisito();
    }
}

