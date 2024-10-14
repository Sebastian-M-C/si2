package com.example.workflow.service;


import com.example.workflow.entity.Cliente;
import com.example.workflow.entity.ClienteTramite;
import com.example.workflow.entity.Tramite;
import com.example.workflow.repository.ClienteTramiteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

//    /*LFB----IMPLEMENTADO*/
//    @Transactional(readOnly=true)
//    public List<Cliente> findAll(){
//        return (List<Cliente>) clienteTramiteRepository.findAll();
//    }
//    public







}