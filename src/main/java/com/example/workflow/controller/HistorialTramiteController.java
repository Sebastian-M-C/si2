package com.example.workflow.controller;

import com.example.workflow.entity.HistorialTramite;
import com.example.workflow.entity.Usuario;
import com.example.workflow.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/historialTramite")
public class HistorialTramiteController {

    @Autowired
    private HistorialTramiteService historialTramiteService;

    @Autowired
    private TramiteService tramiteService;

    @Autowired
    private ClienteTramiteService clienteService;

    @Autowired
    private BitacoraServicio bitacoraServicio;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping({"", "/"})
    public String listarHistoriales(Model model, HttpServletRequest request) {
        List<HistorialTramite> historiales = historialTramiteService.listarHistorial();
        model.addAttribute("historiales", historiales);

        // Registro en bitácora
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioService.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Visualizó la lista de historiales de trámites", dispositivo, ip);

        return "historialTramite/index"; // Asegúrate de que exista esta plantilla Thymeleaf
    }

    @GetMapping("/crear")
    public String crearHistorialForm(Model model) {
        model.addAttribute("historialTramite", new HistorialTramite());
        model.addAttribute("tramites", tramiteService.obtenerTodos());
        model.addAttribute("clientes", clienteService.obtenerTodos());
        return "historialTramite/crear"; // Asegúrate de que exista esta plantilla Thymeleaf
    }

    @PostMapping("/crear")
    public String crearHistorial(@ModelAttribute HistorialTramite historialTramite) {
        historialTramiteService.guardarHistorial(historialTramite);
        return "redirect:/historialTramite";
    }

    @GetMapping("/editar/{id}")
    public String editarHistorialForm(@PathVariable Long id, Model model) {
        HistorialTramite historialTramite = historialTramiteService.obtenerPorId(id).orElse(null);
        if (historialTramite == null) {
            return "redirect:/historialTramite";
        }
        model.addAttribute("historialTramite", historialTramite);
        model.addAttribute("tramites", tramiteService.obtenerTodos());
        model.addAttribute("clientes", clienteService.obtenerTodos());
        return "historialTramite/editar"; // Asegúrate de que exista esta plantilla Thymeleaf
    }

    @PostMapping("/editar/{id}")
    public String editarHistorial(@PathVariable Long id, @ModelAttribute HistorialTramite historialTramite) {
        historialTramiteService.actualizarHistorial(id, historialTramite);
        return "redirect:/historialTramite";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarHistorial(@PathVariable Long id) {
        historialTramiteService.eliminarHistorial(id);
        return "redirect:/historialTramite";
    }

//    @GetMapping("/buscar")
//    public String buscarHistorial(@RequestParam(name = "busqueda", required = false) String busqueda, Model model) {
//        List<HistorialTramite> historiales;
//
//        if (busqueda != null && !busqueda.isEmpty()) {
//            historiales = historialTramiteService.buscarPorNombreOCliente(busqueda);
//        } else {
//            historiales = historialTramiteService.listarHistorial();
//        }
//
//        model.addAttribute("historiales", historiales);
//        model.addAttribute("busqueda", busqueda);
//        return "historialTramite/index"; // Asegúrate de que exista esta plantilla Thymeleaf
//    }

    @GetMapping("/buscar")
    public String buscarHistorial(@RequestParam(name = "busqueda", required = false) String busqueda, Model model) {
        List<HistorialTramite> historiales;

        if (busqueda != null && !busqueda.isEmpty()) {
            historiales = historialTramiteService.buscarPorNombreOCliente(busqueda);
        } else {
            historiales = historialTramiteService.listarHistorial();
        }

        model.addAttribute("historiales", historiales);
        model.addAttribute("busqueda", busqueda);
        return "historialTramite/index"; // Asegúrate de que exista esta plantilla Thymeleaf
    }

}
