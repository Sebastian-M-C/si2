package com.example.workflow.controller;

import com.example.workflow.entity.EstadoTramite;
import com.example.workflow.entity.Requisito;
import com.example.workflow.entity.Tramite;
import com.example.workflow.service.TramiteService;
import com.example.workflow.service.TramiteVerificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class DocumentoController {

    @Autowired
    TramiteService tramiteService;

    @Autowired
    private TramiteVerificacionService tramiteVerificacionService;

    @GetMapping("/documentos")
    public String listarTramitesSinEstado(Model model) {
        List<Tramite> tramitesSinEstado = tramiteVerificacionService.obtenerTramitesSinEstado();
        model.addAttribute("tramites", tramitesSinEstado);
        return "documentos/index"; // Nombre de la vista HTML para mostrar los trámites
    }

    @GetMapping("/documentos/aprobados")
    public String listarTramitesAprobados(Model model) {
        List<Tramite> tramitesAprobados = tramiteVerificacionService.obtenerTramitesAprobados();
        model.addAttribute("tramites", tramitesAprobados);
        return "documentos/aprobados"; // Vista HTML para trámites aprobados
    }

    @GetMapping("/documentos/reprobados")
    public String listarTramitesRechazados(Model model) {
        List<Tramite> tramitesRechazados = tramiteVerificacionService.obtenerTramitesRechazados();
        model.addAttribute("tramites", tramitesRechazados);
        return "documentos/reprobados"; // Vista HTML para trámites rechazados
    }

    // Método para mostrar los detalles del trámite
    @GetMapping("/documentos/verificar/{id}")
    public String mostrarVerificacion(@PathVariable("id") Long tramiteId, Model model) {
        // Obtener el trámite por su ID
        Tramite tramite = tramiteService.obtenerPorId(tramiteId);

        // Aquí suponemos que los requisitos del trámite se obtienen en un atributo llamado "requisitos"
//        List<Requisito> requisitos = tramite.getRequisitos();
        Set<Requisito> requisitos = new HashSet<>(tramite.getRequisitos());

        // Agregar el trámite y los requisitos al modelo
        model.addAttribute("tramite", tramite);
        model.addAttribute("requisitos", requisitos);

        // Devolver la vista de verificación
        return "documentos/verificar";
    }

    // Método para aprobar el trámite
    @PostMapping("/documentos/{id}/aprobar")
    public String aprobarTramite(@PathVariable("id") Long tramiteId) {
        tramiteVerificacionService.aprobarTramite(tramiteId); // Se elimina el segundo argumento
        return "redirect:/documentos"; // Redirige a la lista de documentos
    }


    // Método para rechazar el trámite
    @PostMapping("/documentos/rechazar/{id}")
    public String rechazarTramite(@PathVariable("id") Long tramiteId, @RequestParam("observacion") String observacion) {
        tramiteVerificacionService.rechazarTramite(tramiteId, observacion);
        return "redirect:/documentos";
    }

    @GetMapping("/documentos/estado/{id}")
    public String mostrarEstado(@PathVariable Long id, Model model) {
        Tramite tramite = tramiteVerificacionService.getTramiteById(id);
        model.addAttribute("tramite", tramite);
        model.addAttribute("estados", EstadoTramite.values()); // Enviar todos los estados posibles
        return "documentos/estado";
    }

    @PostMapping("/documentos/estado/{id}")
    public String actualizarEstado(@PathVariable Long id, @RequestParam("estado") EstadoTramite estado) {
        Tramite tramite = tramiteVerificacionService.getTramiteById(id);
        tramite.setEstado(estado); // Actualizar el estado del trámite
        tramiteVerificacionService.guardarTramite(tramite);
        return "redirect:/documentos/aprobados"; // Redirigir a la lista de trámites
    }
}
