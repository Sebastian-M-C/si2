package com.example.workflow.service;


import com.example.workflow.entity.Requisito;
import com.example.workflow.repository.RequisitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequisitoServiceImpl implements RequisitoService{
    @Autowired
    RequisitoRepository requisitoRepository;

    @Override
    public Requisito saveRequisito(Requisito requisito) {
        return requisitoRepository.save(requisito);
    }

    @Override
    public List<Requisito> findAllRequisito() {
        return requisitoRepository.findAll();
    }
}
