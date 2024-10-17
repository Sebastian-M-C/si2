package com.example.workflow.service;

import com.example.workflow.dto.UsuarioRegistroDto;
import com.example.workflow.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UsuarioService extends UserDetailsService {

    List<Usuario> findAll();

    Usuario save(Usuario usuario);

    boolean existsByUsername(String usuario);

    public Usuario guardar (UsuarioRegistroDto registroDto);

    public List<Usuario> listarUsuarios();

    public Usuario encontrarPorNombreUsuario(String nombreUsuario);

    public Long obtenerIdUsuarioPorNombre(String nombreUsuario);

}
