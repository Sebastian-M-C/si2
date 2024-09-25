package com.example.workflow.controller;


import com.example.workflow.entity.Cliente;
import com.example.workflow.entity.ClienteTramite;
import com.example.workflow.entity.Tramite;
import com.example.workflow.repository.ClienteRepository;
import com.example.workflow.repository.TramiteRepository;
import com.example.workflow.service.ClienteTramiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteTramiteController {

    @Autowired
    ClienteTramiteService clienteTramiteService;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TramiteRepository tramiteRepository;

    @PostMapping("/asignarClienteTramite/{clienteId} {tramiteId}")
    public ClienteTramite asignarTramiteACliente(@PathVariable Long clienteId, @PathVariable Long tramiteId) {
        // Aquí puedes obtener los objetos Cliente y Tramite de la base de datos
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow();
        Tramite tramite = tramiteRepository.findById(tramiteId).orElseThrow();

        return clienteTramiteService.saveClienteTramite(cliente, tramite);
    }

}

