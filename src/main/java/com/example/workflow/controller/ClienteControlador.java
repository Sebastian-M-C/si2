package com.example.workflow.controller;

import com.example.workflow.dto.ClienteDto;
import com.example.workflow.entity.Cliente;
import com.example.workflow.entity.Usuario;
import com.example.workflow.repository.ClienteRepository;
import com.example.workflow.service.BitacoraServicio;
import com.example.workflow.service.UsuarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.filter.RequestContextFilter;

import java.util.Date;

@Controller
@RequestMapping("/clientes")
public class ClienteControlador {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private BitacoraServicio bitacoraServicio;
    @Autowired
    private UsuarioService usuarioService;


    private static final Logger logger= LoggerFactory.getLogger(ClienteControlador.class);
    @Autowired
    private RequestContextFilter requestContextFilter;

    @GetMapping({"","/"})
    public String getClientes(Model model, HttpServletRequest request){
        var clientes = clienteRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        model.addAttribute("cliente", clientes);

        //registar en bitacora que el cliente visualizo la lista de clientes
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario=authentication.getName();
        Usuario usuario = usuarioService.encontrarPorNombreUsuario(nombreUsuario);
        String ip=request.getRemoteAddr();
        String dispositivo=request.getHeader("User-Agent");
        bitacoraServicio.regirtrarAccion(usuario,"Visualizo la lista de Cliente", dispositivo,ip);

        return "clientes/index";
    }

    @GetMapping("/crear") // para que me dirija a ese lugar
    public String createCliente(Model model, HttpServletRequest request){
        ClienteDto clienteDto = new ClienteDto();
        model.addAttribute("clienteDto", clienteDto);

        //registar en bitacora que el adm accedio a la pagina de creacion de un new cliente
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario=authentication.getName();
        Usuario usuario = usuarioService.encontrarPorNombreUsuario(nombreUsuario);
        String ip=request.getRemoteAddr();
        String dispositivo=request.getHeader("User-Agent");
        bitacoraServicio.regirtrarAccion(usuario,"Accedio a la pagina de crea un new Cliente", dispositivo,ip);

        return "clientes/crear";
    }

    @PostMapping("/crear")
    public String createCliente(
                    @Valid @ModelAttribute ClienteDto clienteDto,
                    BindingResult result, HttpServletRequest request
    ){
        if(clienteRepository.findByEmail(clienteDto.getEmail())!= null){
            result.addError(
                    new FieldError("clienteDto","email", clienteDto.getEmail(),
                    false, null, null, "Email ya usado")
            );
        }
    if(result.hasErrors()){
        return "clientes/crear";
    }
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellido(clienteDto.getApellido());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setStatus(clienteDto.getStatus());
        cliente.setCreateAt(new Date());

        clienteRepository.save(cliente);

        //registar en bitacora que el adm creo un new cliente
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario=authentication.getName();
        Usuario usuario = usuarioService.encontrarPorNombreUsuario(nombreUsuario);
        String ip=request.getRemoteAddr();
        String dispositivo=request.getHeader("User-Agent");
        bitacoraServicio.regirtrarAccion(usuario,"Creo un new Cliente", dispositivo,ip);

        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(Model model, @PathVariable Long id, HttpServletRequest request){
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if(cliente == null){
            return "redirect:/clientes";
        }

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setApellido(cliente.getApellido());
        clienteDto.setEmail(cliente.getEmail());
        clienteDto.setStatus(cliente.getStatus());

        model.addAttribute("cliente", cliente);
        model.addAttribute("clienteDto", clienteDto);

        //registar en bitacora que el cliente accedio a la pagina de edicion
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario=authentication.getName();
        Usuario usuario = usuarioService.encontrarPorNombreUsuario(nombreUsuario);
        String ip=request.getRemoteAddr();
        String dispositivo=request.getHeader("User-Agent");
        bitacoraServicio.regirtrarAccion(usuario,"Accedio a la pagina de edicion del cliente con ID: "+id, dispositivo,ip);


        return "clientes/editar";
    }

    @PostMapping("/editar/{id}")
    public String editarCliente(
            Model model,
            @PathVariable Long id,
            @Valid @ModelAttribute ClienteDto clienteDto,
            BindingResult result, HttpServletRequest request
    ){
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if(cliente == null){
            return "redirect:/clientes";
        }

        //aqui se usa para el boton guardar th:action="@{/ruta}"
        model.addAttribute("cliente", cliente);

        if(result.hasErrors()){
            return "clientes/editar";
        }

        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellido(clienteDto.getApellido());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setStatus(clienteDto.getStatus());

        try{
            clienteRepository.save(cliente);
        }catch (Exception ex){
            result.addError(
                new FieldError("clienteDto", "email", clienteDto.getEmail(),
                        false, null,null,"El email ya esta usado")
            );
            return "clientes/editar";
        }

        //registar en bitacora que el adm edito un clientes
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario=authentication.getName();
        Usuario usuario = usuarioService.encontrarPorNombreUsuario(nombreUsuario);
        String ip=request.getRemoteAddr();
        String dispositivo=request.getHeader("User-Agent");
        bitacoraServicio.regirtrarAccion(usuario,"Edito el Clientecon ID: "+id, dispositivo,ip);


        return "redirect:/clientes";
    }

    @GetMapping("/eliminar")
    public String eliminarCliente(@RequestParam Long id, HttpServletRequest request){
        Cliente cliente = clienteRepository.findById(id).orElse(null);

        if(cliente != null){
            clienteRepository.delete(cliente);

            //registar en bitacora que el adm elimino a un cliente
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String nombreUsuario=authentication.getName();
            Usuario usuario = usuarioService.encontrarPorNombreUsuario(nombreUsuario);
            String ip=request.getRemoteAddr();
            String dispositivo=request.getHeader("User-Agent");
            bitacoraServicio.regirtrarAccion(usuario,nombreUsuario, dispositivo,ip);

        }

        return "redirect:/clientes";

    }

}
