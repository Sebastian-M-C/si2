package com.example.workflow.controller;

import com.example.workflow.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/enviar-correo")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public Object enviarCorreo(
            @RequestParam("nombre") String nombre,
            @RequestParam("email") String email,
            @RequestParam("mensaje") String mensaje,
            @RequestParam(value = "isApi", required = false, defaultValue = "false") boolean isApi
    ){
        String subject = "Nuevo mensaje de: " + nombre;
        String message =  "Detalles del mensaje:\n\n" + mensaje + "\n\nContacto: " + email;

        // Enviar el correo
        emailService.sendSimpleEmail("sebastianmojica215@gmail.com", subject, message);

        // Si la solicitud es para API, devolver JSON, si no, redirigir
        if (isApi) {
            return "{\"status\":\"success\",\"message\":\"Correo enviado correctamente\"}";
        } else {
            return new ModelAndView("redirect:/tramites/catastro");
        }
    }
}
