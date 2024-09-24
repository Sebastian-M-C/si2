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
@RequestMapping("/usuario")
public class ClienteControlador {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @GetMapping({"/",""})
    public String getClientes(Model model){
        var cliente = clienteRepositorio.findAll(Sort.by(Sort.Direction.DESC,"id"));
        model.addAttribute("cliente", cliente);
        return "index";
    }

    @GetMapping("/crear") // para que me dirija a ese lugar
    public String creatCliente(Model model){
        ClienteDto clienteDto = new ClienteDto();
        model.addAttribute("clienteDto", clienteDto);
        return "clientes/crear";
    }

    @PostMapping("/crear")
    public String crearCliente(
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
        cliente.setEstados(clienteDto.getStatus());
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
        clienteDto.setStatus(cliente.getEstados());

        model.addAttribute("cliente", cliente);
        model.addAttribute("clienteDto", clienteDto);

        return "redirect:/clientes/editar";
    }
}
