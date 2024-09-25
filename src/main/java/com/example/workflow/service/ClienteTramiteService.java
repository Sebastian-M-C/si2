package com.example.workflow.service;

import com.example.workflow.entity.Cliente;
import com.example.workflow.entity.ClienteTramite;
import com.example.workflow.entity.Tramite;

public interface ClienteTramiteService {
    public ClienteTramite saveClienteTramite(Cliente cliente, Tramite tramite);
}

