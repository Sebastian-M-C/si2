package com.example.workflow.service;

import com.example.workflow.entity.EstadoDocumentos;
import com.example.workflow.entity.Tramite;
import com.example.workflow.repository.TramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TramiteVerificacionService {

    @Autowired
    private TramiteRepository tramiteRepository;

    public List<Tramite> obtenerTramitesSinEstado() {
        return tramiteRepository.findByEstadoDocumentosIsNull();
    }

    public List<Tramite> obtenerTramitesAprobados() {
        return tramiteRepository.findByEstadoDocumentosAprobado();
    }

    public List<Tramite> obtenerTramitesRechazados() {
        return tramiteRepository.findByEstadoDocumentosRechazado();
    }

    // Método para aprobar el trámite
    public void aprobarTramite(Long tramiteId) {
        Tramite tramite = tramiteRepository.findById(tramiteId)
                .orElseThrow(() -> new RuntimeException("Trámite no encontrado"));
        tramite.setEstadoDocumentos(EstadoDocumentos.APROBADO); // Asigna el estado APROBADO
        tramiteRepository.save(tramite); // Guarda el cambio
    }

    // Método para rechazar el trámite
    public void rechazarTramite(Long tramiteId, String motivoRechazo) {
        Tramite tramite = tramiteRepository.findById(tramiteId)
                .orElseThrow(() -> new RuntimeException("Trámite no encontrado"));
        tramite.setEstadoDocumentos(EstadoDocumentos.RECHAZADO);  // Usamos el valor del enum
        tramite.setMotivoRechazo(motivoRechazo);  // Usamos el campo 'motivoRechazo' para almacenar la observación
        tramiteRepository.save(tramite);
    }
}
