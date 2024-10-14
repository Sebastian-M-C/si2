package com.example.workflow.service;


import com.example.workflow.entity.Tramite;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TramiteService {
    Tramite saveTramite(Tramite tramite);
    Tramite updateTramite(Long id, Tramite tramite);
    void deleteTramite(Long id);
    Optional<Tramite> findTramiteByID(Long id);
}