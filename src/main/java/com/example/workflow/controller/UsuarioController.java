package com.example.workflow.controller;

import com.example.workflow.dto.UsuarioRegistroDto;
import com.example.workflow.entity.Usuario;
import com.example.workflow.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RestController
//@RequestMapping("/usuarios")
@Controller
@RequestMapping("/registro")
public class UsuarioController {

//    @Autowired
    private UsuarioService service;

    public UsuarioController(UsuarioService usuarioService) {
        super();
        this.service = usuarioService;
    }

    @ModelAttribute("usuario")
    public UsuarioRegistroDto retornarNuevoUsuarioRegistroDTO(){
        return new UsuarioRegistroDto();
    }

    @GetMapping
    public String mostrarFormualarioDeRegistro() {
        return "registro";
    }

    @PostMapping
    public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDto registroDto) {
        service.guardar(registroDto);
        return "redirect:/registro?exito";

    }

    // Nuevo método para mostrar la lista de usuarios
    @GetMapping("/usuarios")
    public String verUsuarios(Model model) {
        List<Usuario> usuarios = service.obtenerTodosLosUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }











//    @GetMapping
//    public List<Usuario> list() {
//        return service.findAll();
//    }
//
//    //@PreAuthorize("hasRole('ADMIN')")
//    @PostMapping
//    public ResponseEntity<?> create(@Valid @RequestBody Usuario user, BindingResult result) {
//        if (result.hasFieldErrors()) {
//            return validation(result);
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
//    }
//
//    @PostMapping("/registrar")
//    public ResponseEntity<?> register(@Valid @RequestBody Usuario user, BindingResult result) {
//        //user.setAdmin(true);
//        return create(user, result);
//    }
//
//    private ResponseEntity<?> validation(BindingResult result) {
//        Map<String, String> errors = new HashMap<>();
//
//        result.getFieldErrors().forEach(err -> {
//            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
//        });
//        return ResponseEntity.badRequest().body(errors);
//    }
}
