package com.example.workflow.service;

import com.example.workflow.entity.MonitoreoPlazos;
import com.example.workflow.entity.Tramite;
import com.example.workflow.repository.MonitoreoPlazosRepository;
import com.example.workflow.repository.TramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonitoreoPlazosServiceImpl implements  MonitoreoPlazosService{

    @Autowired
    private MonitoreoPlazosRepository monitoreoPlazoRepository;

    @Autowired
    private TramiteRepository tramiteRepository;

    @Override
    public MonitoreoPlazos saveMonitoreoPlazo(MonitoreoPlazos monitoreoPlazo) {
        return monitoreoPlazoRepository.save(monitoreoPlazo);
    }

    @Override
    public MonitoreoPlazos updateMonitoreoPlazo(Long id, MonitoreoPlazos monitoreoPlazo) {
        Optional<MonitoreoPlazos> existingMonitoreo = monitoreoPlazoRepository.findById(id);
        if (existingMonitoreo.isPresent()) {
            MonitoreoPlazos updatedMonitoreo = existingMonitoreo.get();
            updatedMonitoreo.setFechaInicio(monitoreoPlazo.getFechaInicio());
            updatedMonitoreo.setFechaVencimiento(monitoreoPlazo.getFechaVencimiento());
            updatedMonitoreo.setEstado(monitoreoPlazo.getEstado());
            updatedMonitoreo.setCliente(monitoreoPlazo.getCliente());
            updatedMonitoreo.setTramite(monitoreoPlazo.getTramite());
            return monitoreoPlazoRepository.save(updatedMonitoreo);
        }
        return null;
    }

    @Override
    public void deleteMonitoreoPlazo(Long id) {
        monitoreoPlazoRepository.deleteById(id);
    }

    @Override
    public Optional<MonitoreoPlazos> findById(Long id) {
        return monitoreoPlazoRepository.findById(id);
    }

    @Override
    public List<MonitoreoPlazos> findByClienteId(Long clienteId) {
        return monitoreoPlazoRepository.findByClienteId(clienteId);
    }

    @Override
    public List<MonitoreoPlazos> findByTramiteId(Long tramiteId) {
        return monitoreoPlazoRepository.findByTramiteId(tramiteId);
    }

    @Override
    public List<MonitoreoPlazos> findAll() {
        return List.of();
    }

    public List<Tramite> obtenerTodosLosTramites() {
        return tramiteRepository.findAllWithClientes();
    }


}
