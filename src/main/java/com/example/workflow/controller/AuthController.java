package com.example.workflow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // Aquí validas el usuario y la contraseña y generas un token si son válidos
        String token = "token generado"; // Lógica para generar el token
        return ResponseEntity.ok(token);
    }

    // Clase interna para la solicitud de login
    public static class LoginRequest {
        private String username;
        private String password;

        // Getters y Setters
    }
}
