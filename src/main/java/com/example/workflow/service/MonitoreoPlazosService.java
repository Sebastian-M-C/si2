package com.example.workflow.service;

import com.example.workflow.entity.MonitoreoPlazos;
import com.example.workflow.entity.Tramite;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MonitoreoPlazosService {
    MonitoreoPlazos saveMonitoreoPlazo(MonitoreoPlazos  monitoreoPlazo);
    MonitoreoPlazos updateMonitoreoPlazo(Long id, MonitoreoPlazos monitoreoPlazo);
    void deleteMonitoreoPlazo(Long id);
    Optional<MonitoreoPlazos> findById(Long id);
    List<MonitoreoPlazos> findByClienteId(Long clienteId);
    List<MonitoreoPlazos> findByTramiteId(Long tramiteId);
    List<MonitoreoPlazos> findAll();
    List<Tramite> obtenerTodosLosTramites();
}
