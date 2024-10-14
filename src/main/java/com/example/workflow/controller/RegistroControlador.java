package com.example.workflow.controller;


import com.example.workflow.entity.Bitacora;
import com.example.workflow.entity.Usuario;
import com.example.workflow.service.BitacoraServicio;
import com.example.workflow.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistroControlador {
    private static final Logger logger= LoggerFactory.getLogger(RegistroControlador.class);

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BitacoraServicio bitacoraServicio;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/login")
    public String iniciarSesion(){
        return "login";
    }

    @PostMapping("/login")
    public String iniciarSesionIndex(){
        return "redirect/";
    }

//    @GetMapping("/")
//    public String verPaginacionInicio(Model modelo){
//        registrarAccion("Accedio a la pagina de inicio");
//        modelo.addAttribute("usuarios", usuarioService.findAll());
//        return "index";
//    }

    @PostMapping("/logout")
    public String cerrarSesion(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario=authentication.getName();
        Usuario usuario=usuarioService.encontrarPorNombreUsuario(nombreUsuario);
        String ip=obtenerDireccionIP();
        String dispositivo=obtenerInformacionDispositivo();
        Bitacora bitacora= new Bitacora();
        bitacora.setUsuario(usuario);
        bitacora.setEvento("Cerrar Sesion");
        bitacora.setIp(ip);
        bitacora.setDetalle(dispositivo);
        bitacoraServicio.registrarBitacora(bitacora);
        SecurityContextHolder.clearContext();
        return "redirect:/login?logout";
    }

    private void registrarAccion(String evento){
        try{
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String nombreUsuario=authentication.getName();
            Usuario usuario=usuarioService.encontrarPorNombreUsuario(nombreUsuario);
            String ip=obtenerDireccionIP();
            String dispositivo=obtenerInformacionDispositivo();
            Bitacora bitacora= new Bitacora();
            bitacora.setUsuario(usuario);
            bitacora.setEvento(evento);
            bitacora.setIp(ip);
            bitacora.setDetalle(dispositivo);
            bitacoraServicio.registrarBitacora(bitacora);

        }catch (Exception e){
            logger.error("Error al regitrar accion en la bitacora: ", e);
        }
    }

    private String obtenerDireccionIP(){
        String ipAddress=request.getHeader("X-FORWARDED-FOR");
        if(ipAddress!=null && ipAddress.contains(",")){
            ipAddress=ipAddress.split(",")[0].trim();
        }
        if(ipAddress == null || ipAddress.isEmpty()){
            ipAddress=request.getRemoteAddr();
        }
        return ipAddress;
    }

    private String obtenerInformacionDispositivo(){
        return request.getHeader("User-Agent");
    }

}
