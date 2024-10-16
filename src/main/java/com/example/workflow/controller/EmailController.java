package com.example.workflow.controller;

import com.example.workflow.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/enviar-correo")
    public String enviarCorreo(
            @RequestParam("nombre") String nombre,
            @RequestParam("email") String email,
            @RequestParam("mensaje") String mensaje,
            Model model
    ){
        String subject = "Nuevo mensaje de: " + nombre;
        String message =  "Detalles del mensaje:\n\n" + mensaje + "\n\nContacto: " + email;

        // Enviar el correo
        emailService.sendSimpleEmail("sebastianmojica215@gmail.com", subject, message);

        // Agregar un mensaje de éxito
        model.addAttribute("success", "El correo se ha enviado correctamente.");

        // Redirigir a la página de requisitos/index
        return "redirect:/tramites/catastro";  // Redirige a la ruta que muestra la página 'requisitos/index'
    }
}
