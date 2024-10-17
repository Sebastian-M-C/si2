package com.example.workflow.service;

import com.example.workflow.entity.Tramite;
import com.example.workflow.repository.TramiteRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReporteServiceImpl implements ReporteService{


    @Autowired
    private TramiteRepository tramiteRepository;

    @Override
    public byte[] generarReporteTramites(Long clienteId, Long categoriaId,
                                          LocalDate fechaInicio,
                                         LocalDate fechaFin) throws JRException {

        //optener los tramites segun los filtros
        List<Tramite> tramites = tramiteRepository.findTramitesByFilters(clienteId, categoriaId,
                fechaInicio, fechaFin);

        InputStream reporteStream = this.getClass().getResourceAsStream("/reporte/reportes_tramites.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reporteStream);

        // mapear los datos a Jasperreports
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(tramites);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "workflow");

        // rellenar el reporte
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // exportar a PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);

    }
}
