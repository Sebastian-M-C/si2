package com.example.workflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
//    @GetMapping("/login")
//    public String showLoginForm() {
//        return "login"; // El formulario login.html
//    }
//
//    @PostMapping("/login")
//    public ModelAndView login(@RequestParam String username, @RequestParam String password) {
//        // Autenticación básica (puedes reemplazar esto con tu lógica real)
//        if ("admin".equals(username) && "password".equals(password)) {
//            return new ModelAndView("redirect:/index");
//        } else {
//            ModelAndView mav = new ModelAndView("login");
//            mav.addObject("error", "Usuario o contraseña incorrectos");
//            return mav;
//        }
//    }
//
//    @GetMapping("/index")
//    public String showDashboard() {
//        return "index"; // El dashboard principal
//    }
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Renderiza login.html
    }

    @GetMapping("/index")
    public String showDashboard() {
        return "index"; // El dashboard principal
    }

}
