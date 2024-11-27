package com.example.workflow.service;

import com.example.workflow.entity.HistorialTramite;
import com.example.workflow.entity.Tramite;
import com.example.workflow.repository.HistorialTramiteRepository;
import com.example.workflow.repository.TramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HistorialTramiteServiceImpl implements HistorialTramiteService {

    @Autowired
    private HistorialTramiteRepository historialTramiteRepository;

    @Autowired
    private TramiteRepository tramiteRepository;

    @Override
    public List<HistorialTramite> listarHistorial() {
        return historialTramiteRepository.findAll();
    }

    @Override
    public HistorialTramite guardarHistorial(HistorialTramite historialTramite) {
        historialTramite.setFechaRegistro(LocalDateTime.now());
        return historialTramiteRepository.save(historialTramite);
    }

    @Override
    public Optional<HistorialTramite> obtenerPorId(Long id) {
        return historialTramiteRepository.findById(id);
    }

    @Override
    public void eliminarHistorial(Long id) {
        historialTramiteRepository.deleteById(id);
    }

    @Override
    public void actualizarHistorial(Long id, HistorialTramite historialTramite) {

    }

    public List<HistorialTramite> obtenerTodosLosTramites() {
        return historialTramiteRepository.findAll();
    }

    @Override
    public List<HistorialTramite> buscarPorNombreOCliente(String busqueda) {
        return List.of();
    }

//    @Override
//    public List<HistorialTramite> buscarPorNombreOCliente(String busqueda) {
//        return List.of();
//    }

    @Override
    public List<HistorialTramite> findAll() {
        return List.of();
    }

    @Override
    public List<HistorialTramite> findByTramiteClienteNombreContainingIgnoreCaseOrTramiteNombreContainingIgnoreCaseOrDepartamentoContainingIgnoreCase(String cliente, String tramite, String departamento) {
        return List.of();
    }

//    @Override
//    public List<Tramite> buscarPorNombreOCliente(String busqueda) {
//        return tramiteRepository.findByNombreContainingIgnoreCaseOrCliente_NombreContainingIgnoreCase(busqueda, busqueda);
//    }

//    public List<HistorialTramite> buscarPorNombreOCliente(String busqueda) {
//        return historialTramiteRepository.findByTramiteClienteNombreContainingIgnoreCaseOrTramiteNombreContainingIgnoreCaseOrDepartamentoContainingIgnoreCase(
//                busqueda, busqueda, busqueda);
//    }

}