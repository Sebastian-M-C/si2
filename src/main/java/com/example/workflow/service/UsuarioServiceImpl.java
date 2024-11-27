package com.example.workflow.service;

import com.example.workflow.dto.UsuarioRegistroDto;
import com.example.workflow.entity.Role;
import com.example.workflow.entity.Usuario;
import com.example.workflow.repository.RoleRepository;
import com.example.workflow.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService{

//    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) repository.findAll();
    }

    @Override
    public Usuario save(Usuario usuario) {
        return null;
    }

//    @Override
//    @Transactional
//    public Usuario save(Usuario usuario) {
//
//        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
//        List<Role> roles = new ArrayList<>();
//
//        optionalRoleUser.ifPresent(roles::add);
//
//        if (usuario.isAdmin()) {
//            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
//            optionalRoleAdmin.ifPresent(roles::add);
//        }
//
//        usuario.setRoles(roles);
//        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
//        return repository.save(usuario);
//    }

    public UsuarioServiceImpl(UsuarioRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Usuario guardar(UsuarioRegistroDto registroDto) {
        Usuario usuario = new Usuario(registroDto.getNombre(),
                registroDto.getApellido(),registroDto.getEmail(),
                passwordEncoder.encode(registroDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
        return repository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByEmail(username);
        if(usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        return new User(usuario.getEmail(),usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }


//    @Override
//    public boolean existsByUsername(String usuario) {
//        return repository.existsByUsername(usuario);
//    }

//    @Override
//    public Usuario guardar(UsuarioRegistroDto registroDto) {
//        return null;
//    }

    @Override
    public List<Usuario> listarUsuarios() {
        return List.of();
    }

//    @Override
//    public Usuario encontrarPorNombreUsuario(String nombreUsuario) {
//        return null;
//    }

    @Override
    public Usuario encontrarPorNombreUsuario(String nombreUsuario) {
        return repository.findByEmail(nombreUsuario);
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return List.of();
    }


    @Override
    public Long obtenerIdUsuarioPorNombre(String nombreUsuario) {
        return 0L;
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }
}
