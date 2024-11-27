package com.example.workflow.service;

import com.example.workflow.entity.HistorialTramite;
import com.example.workflow.entity.Tramite;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;
import java.util.Optional;

public interface HistorialTramiteService {
    List<HistorialTramite> listarHistorial();
    HistorialTramite guardarHistorial(HistorialTramite historialTramite);
    Optional<HistorialTramite> obtenerPorId(Long id);
    void eliminarHistorial(Long id);

    void actualizarHistorial(Long id, HistorialTramite historialTramite);
    List<HistorialTramite> obtenerTodosLosTramites();

    List<HistorialTramite> buscarPorNombreOCliente(String busqueda);

    @EntityGraph(attributePaths = {"tramite", "tramite.cliente"})
    List<HistorialTramite> findAll();

    public List<HistorialTramite> findByTramiteClienteNombreContainingIgnoreCaseOrTramiteNombreContainingIgnoreCaseOrDepartamentoContainingIgnoreCase(String cliente, String tramite, String departamento);


}