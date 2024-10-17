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
    public String doc(HttpServletRequest request){
        // Aquí debes devolver el nombre de la plantilla (sin la extensión .html)

        //Regitrar en la bitacora que cliente visualizo la lista de categorias
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioService.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Visualizó la lista de categorias", dispositivo, ip);

        return "tramites/index";  // Asegúrate de que este archivo esté en la carpeta correcta
    }
}
