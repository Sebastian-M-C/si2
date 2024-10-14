package com.example.workflow.service;

import com.example.workflow.entity.Usuario;

import java.util.List;


public interface UsuarioService {

    List<Usuario> findAll();

    Usuario save(Usuario usuario);

    boolean existsByUsername(String usuario);
}
