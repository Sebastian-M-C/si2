package com.example.workflow.service;

import com.example.workflow.entity.Requisito;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RequisitoService {

    Requisito saveRequisito(Requisito requisito);
    List<Requisito> findAllRequisito();
}