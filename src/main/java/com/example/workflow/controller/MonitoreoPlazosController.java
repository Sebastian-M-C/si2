package com.example.workflow.controller;


import com.example.workflow.entity.MonitoreoPlazos;
import com.example.workflow.entity.Tramite;
import com.example.workflow.repository.TramiteRepository;
import com.example.workflow.service.MonitoreoPlazosService;
import com.example.workflow.service.TramiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/monitoreo")
public class MonitoreoPlazosController {

    @Autowired
    private MonitoreoPlazosService monitoreoPlazosService;

    @Autowired
    private TramiteService tramiteService;


//    @GetMapping({"","/"})
//    public String listarMonitoreos(Model model) {
//        List<MonitoreoPlazos> monitoreos = monitoreoPlazosService.findAll();
//        model.addAttribute("monitoreos", monitoreos);
//        return "monitoreo/index";
//    }

    @GetMapping({"","/"})
    public String listarMonitoreos(Model model) {
        List<Tramite> tramites = monitoreoPlazosService.obtenerTodosLosTramites();
        model.addAttribute("tramites", tramites); // Pasamos la lista de trámites al modelo
        return "monitoreo/index";
    }

    @GetMapping("/crear")
    public String crearMonitoreoForm(Model model) {
        model.addAttribute("monitoreoPlazo", new MonitoreoPlazos());
        return "monitoreo/crear";
    }

    @PostMapping("/crear")
    public String crearMonitoreo(@ModelAttribute MonitoreoPlazos monitoreoPlazo) {
        monitoreoPlazosService.saveMonitoreoPlazo(monitoreoPlazo);
        return "redirect:/monitoreoPlazo";
    }

    @GetMapping("/editar/{id}")
    public String editarMonitoreoForm(@PathVariable Long id, Model model) {
        MonitoreoPlazos monitoreoPlazo = monitoreoPlazosService.findById(id).orElse(null);
        model.addAttribute("monitoreoPlazo", monitoreoPlazo);
        return "monitoreo/editar";
    }

    @PostMapping("/editar/{id}")
    public String editarMonitoreo(@PathVariable Long id, @ModelAttribute MonitoreoPlazos monitoreoPlazo) {
        monitoreoPlazosService.updateMonitoreoPlazo(id, monitoreoPlazo);
        return "redirect:/monitoreoPlazo";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMonitoreo(@PathVariable Long id) {
        monitoreoPlazosService.deleteMonitoreoPlazo(id);
        return "redirect:/monitoreoPlazo";
    }




}
