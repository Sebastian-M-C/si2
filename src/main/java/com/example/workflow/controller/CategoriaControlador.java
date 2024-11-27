package com.example.workflow.controller;

import com.example.workflow.entity.Usuario;
import com.example.workflow.service.BitacoraServicio;
import com.example.workflow.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tramites")
public class CategoriaControlador {

    @Autowired
    private BitacoraServicio bitacoraServicio;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String doc(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioService.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Visualizó la lista de categorias", dispositivo, ip);

        return "tramites/index";
    }
}