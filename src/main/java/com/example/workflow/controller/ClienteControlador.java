package com.example.workflow.controller;

import com.example.workflow.dto.ClienteDto;
import com.example.workflow.entity.Cliente;
import com.example.workflow.entity.Usuario;
import com.example.workflow.repository.ClienteRepositorio;
import com.example.workflow.service.BitacoraServicio;
import com.example.workflow.service.UsuarioService;
import jakarta.validation.Valid;
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

import java.util.Date;

@Controller
@RequestMapping("/clientes")
public class ClienteControlador {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private BitacoraServicio bitacoraServicio;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping({"","/"})
    public String getClientes(Model model, HttpServletRequest request){
        var clientes = clienteRepositorio.findAll(Sort.by(Sort.Direction.DESC,"id"));
        model.addAttribute("cliente", clientes);

        // Registrar en bitácora que el cliente visualizó la lista de clientes

        // String usuario =
        // SecurityContextHolder.getContext().getAuthentication().getName();

        //Regitrar en la bitacora que cliente visualizo la lista de cliente
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioService.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Visualizó la lista de clientes", dispositivo, ip);

        return "clientes/index";
    }

    @GetMapping("/crear") // para que me dirija a ese lugar
    public String createCliente(Model model, HttpServletRequest request){
        ClienteDto clienteDto = new ClienteDto();
        model.addAttribute("clienteDto", clienteDto);


        //Regitrar en la bitacora que admin visualizo la pagina par crear cliente

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioService.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Accedio a la pagina de crear new cliente", dispositivo, ip);

        return "clientes/crear";
    }

    @PostMapping("/crear")
    public String createCliente(
                    @Valid @ModelAttribute ClienteDto clienteDto,
                    BindingResult result, HttpServletRequest request
    ){
        if(clienteRepositorio.findByEmail(clienteDto.getEmail())!= null){
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

        clienteRepositorio.save(cliente);

        //Regitrar en la bitacora que admin creo un new cliente

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioService.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "creo un new cliente", dispositivo, ip);

        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(Model model, @PathVariable Long id, HttpServletRequest request ){
        Cliente cliente = clienteRepositorio.findById(id).orElse(null);
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


        //Regitrar en la bitacora que admin pagina de edicion del cliente

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioService.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Accedió a la página de edición del cliente con ID: " + id,
                dispositivo, ip);

        return "clientes/editar";
    }

    @PostMapping("/editar/{id}")
    public String editarCliente(
            Model model,
            @PathVariable Long id,
            @Valid @ModelAttribute ClienteDto clienteDto,
            BindingResult result, HttpServletRequest request
    ){
        Cliente cliente = clienteRepositorio.findById(id).orElse(null);
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
            clienteRepositorio.save(cliente);
        }catch (Exception ex){
            result.addError(
                new FieldError("clienteDto", "email", clienteDto.getEmail(),
                        false, null,null,"El email ya esta usado")
            );
            return "clientes/editar";
        }

        //Regitrar en la bitacora que admin edito un cliente

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioService.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, "Edito al cliente con ID: " + id,
                dispositivo, ip);

      return "redirect:/clientes";
}

    @GetMapping("/eliminar")
    public String eliminarCliente(@RequestParam Long id, HttpServletRequest request){
        Cliente cliente = clienteRepositorio.findById(id).orElse(null);

        if(cliente != null){
            clienteRepositorio.delete(cliente);
        }


        //Regitrar en la bitacora que admin elimino un cliente

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();
        Usuario usuario = usuarioService.encontrarPorNombreUsuario(nombreUsuario);
        String ip = request.getRemoteAddr();
        String dispositivo = request.getHeader("User-Agent");
        bitacoraServicio.registrarAccion(usuario, nombreUsuario, dispositivo, ip);

        return "redirect:/clientes";

    }

}
