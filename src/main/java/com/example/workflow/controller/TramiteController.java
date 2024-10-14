package com.example.workflow.controller;


import com.example.workflow.dto.TramiteDto;
import com.example.workflow.entity.Tramite;
import com.example.workflow.repository.TramiteRepository;
import com.example.workflow.service.TramiteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class TramiteController {

    @Autowired
    TramiteService tramiteService;

    @Autowired
    public TramiteRepository tramiteRepository;

    @GetMapping("/tramites/catastro")
    public String getListOfDocs(Model model) {
        var tramites = tramiteRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("tramites", tramites);
        return "/tramites/catastro";
    }


    @GetMapping("tramites/crear")
    public String createDoc(Model model){
        TramiteDto tramiteDto = new TramiteDto();
        model.addAttribute("tramiteDto", tramiteDto);
        return "tramites/crear";
    }

    @PostMapping("tramites/crear")
    public String createDoc(
            @Valid @ModelAttribute TramiteDto tramiteDto,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("tramiteDto", tramiteDto);  // volver a enviar el DTO con errores
            return "/tramites/crear";  // volver a la vista del formulario
        }

        Tramite tramite = new Tramite();
        tramite.setNombre(tramiteDto.getNombre());
        tramite.setDescripcion(tramiteDto.getDescripcion());

        tramiteRepository.save(tramite);

        return "redirect:/tramites/catastro";
    }

}