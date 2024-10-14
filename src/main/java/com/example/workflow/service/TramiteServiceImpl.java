package com.example.workflow.service;


import com.example.workflow.entity.Tramite;
import com.example.workflow.repository.TramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class TramiteServiceImpl implements TramiteService{
    @Autowired
    TramiteRepository tramiteRepository;

    @Override
    public Tramite saveTramite(Tramite tramite) {
        return tramiteRepository.save(tramite);
    }

    @Override
    public Tramite updateTramite(Long id, Tramite tramite) {
        Tramite localDb = tramiteRepository.findById(id).get();
        if(Objects.nonNull(tramite.getNombre()) && !"".equalsIgnoreCase(tramite.getNombre())){
            localDb.setNombre(tramite.getNombre());
        }
        if(Objects.nonNull(tramite.getDescripcion()) && !"".equalsIgnoreCase(tramite.getDescripcion())){
            localDb.setDescripcion(tramite.getDescripcion());
        }
        return tramiteRepository.save(localDb);
    }

    @Override
    public void deleteTramite(Long id) {
        tramiteRepository.deleteById(id);
    }

    @Override
    public Optional<Tramite> findTramiteByID(Long id) {
        return tramiteRepository.findById(id);
    }

}

