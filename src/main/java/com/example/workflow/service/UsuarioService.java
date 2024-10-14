package com.example.workflow.service;


import com.example.workflow.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioService extends UserDetails {
     List<Usuario> findAll();
    Usuario encontrarPorNombreUsuario(String nombreUsuario);
    Usuario save(Usuario usuario);
    //Long obtenerIdUsuarioPorNombre(String nombreUsuario);
    boolean existsByUsername(String usuario);

}
