package com.example.workflow.controller;

import com.example.workflow.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/asignacion_de_tramite")
public class AsignacionController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/enviar-correo")
    public String mostrarFormulario() {
        return "asignacion_de_tramite/enviar-correo"; // Devuelve la vista del formulario
    }

    @PostMapping("/enviar-correo")
    public String enviar(
            @RequestParam("correo") String correo,
            @RequestParam("telefono") String telefono,
            @RequestParam("motivo") String motivo,
            Model model) {

        // Crear el mensaje de correo con los datos ingresados
        String mensaje = "Teléfono de contacto: " + telefono + "\nMotivo: " + motivo;

        // Enviar el correo
        emailService.sendSimpleEmail(correo, "Asignación de Trámites", mensaje);

        model.addAttribute("mensaje", "Correo enviado exitosamente a " + correo);
        return "asignacion_de_tramite/enviar-correo"; // Regresa a la vista del formulario
    }

}
