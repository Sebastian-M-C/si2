package com.example.workflow.controller;

import com.example.workflow.dto.ClienteDto;
import com.example.workflow.entity.Cliente;
import com.example.workflow.repository.ClienteRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/clientes")
public class ClienteControlador {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @GetMapping({"","/"})
    public String getClientes(Model model){
        var clientes = clienteRepositorio.findAll(Sort.by(Sort.Direction.DESC,"id"));
        model.addAttribute("cliente", clientes);
        return "clientes/index";
    }

    @GetMapping("/crear") // para que me dirija a ese lugar
    public String createCliente(Model model){
        ClienteDto clienteDto = new ClienteDto();
        model.addAttribute("clienteDto", clienteDto);
        return "clientes/crear";
    }

    @PostMapping("/crear")
    public String createCliente(
                    @Valid @ModelAttribute ClienteDto clienteDto,
                    BindingResult result
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

        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(Model model, @PathVariable Long id){
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

        return "clientes/editar";
    }

    @PostMapping("/editar/{id}")
    public String editarCliente(
            Model model,
            @PathVariable Long id,
            @Valid @ModelAttribute ClienteDto clienteDto,
            BindingResult result
    ){
        Cliente cliente = clienteRepositorio.findById(id).orElse(null);
        if(cliente == null){
            return "redirect:/clientes";
        }

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

        return "redirect:/clientes";
    }

    @GetMapping("/eliminar")
    public String eliminarCliente(@RequestParam Long id){
        Cliente cliente = clienteRepositorio.findById(id).orElse(null);

        if(cliente != null){
            clienteRepositorio.delete(cliente);
        }

        return "redirect:/clientes";

    }

}
