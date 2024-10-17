package com.example.workflow.service;

import net.sf.jasperreports.engine.JRException;

import java.time.LocalDate;

public interface ReporteService {

    //String estado = "EN_PROCESO"; // Aquí pasas como String
    byte[] generarReporteTramites(Long clienteId, Long categoriaId,
                                   LocalDate fechaInicio,
                                  LocalDate fechaFin) throws JRException;
}
