package com.example.workflow.service;


import com.example.workflow.entity.Cliente;
import com.example.workflow.entity.ClienteTramite;
import com.example.workflow.entity.Tramite;
import com.example.workflow.repository.ClienteTramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteTramiteServiceImpl implements ClienteTramiteService {
    @Autowired
    ClienteTramiteRepository clienteTramiteRepository;

    @Override
    public ClienteTramite saveClienteTramite(Cliente cliente, Tramite tramite) {
        ClienteTramite clienteTramite = new ClienteTramite();
        clienteTramite.setCliente(cliente);
        clienteTramite.setTramite(tramite);
        return clienteTramiteRepository.save(clienteTramite);
    }
}