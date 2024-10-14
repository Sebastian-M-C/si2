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
    public String getListOfDocsCatastro(Model model) {
        var tramites = tramiteRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("tramites", tramites);
        return "/tramites/catastro";
    }


    @GetMapping("tramites/crear")
    public String createDocCatastro(Model model){
        TramiteDto tramiteDto = new TramiteDto();
        model.addAttribute("tramiteDto", tramiteDto);
        return "tramites/crear";
    }

    @PostMapping("tramites/crear")
    public String createDocCatastro(
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

    @GetMapping("/tramites/juridica")
    public String getListOfDocsJuridica(Model model) {
        var tramites = tramiteRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("tramites", tramites);
        return "/tramites/juridica";
    }


    @GetMapping("tramites/juridicaCrear")
    public String createDocJuridica(Model model){
        TramiteDto tramiteDto = new TramiteDto();
        model.addAttribute("tramiteDto", tramiteDto);
        return "tramites/juridicaCrear";
    }

    @PostMapping("tramites/juridicaCrear")
    public String createDocJuridica(
            @Valid @ModelAttribute TramiteDto tramiteDto,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("tramiteDto", tramiteDto);  // volver a enviar el DTO con errores
            return "/tramites/juridicaCrear";  // volver a la vista del formulario
        }

        Tramite tramite = new Tramite();
        tramite.setNombre(tramiteDto.getNombre());
        tramite.setDescripcion(tramiteDto.getDescripcion());

        tramiteRepository.save(tramite);

        return "redirect:/tramites/juridica";
    }

    @GetMapping("/tramites/saneamiento")
    public String getListOfDocsSaneamiento(Model model) {
        var tramites = tramiteRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("tramites", tramites);
        return "/tramites/saneamiento";
    }


    @GetMapping("tramites/saneamientoCrear")
    public String createDocSaneamiento(Model model){
        TramiteDto tramiteDto = new TramiteDto();
        model.addAttribute("tramiteDto", tramiteDto);
        return "tramites/saneamientoCrear";
    }

    @PostMapping("tramites/saneamientoCrear")
    public String createDocSaneamiento(
            @Valid @ModelAttribute TramiteDto tramiteDto,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("tramiteDto", tramiteDto);  // volver a enviar el DTO con errores
            return "/tramites/saneamientoCrear";  // volver a la vista del formulario
        }

        Tramite tramite = new Tramite();
        tramite.setNombre(tramiteDto.getNombre());
        tramite.setDescripcion(tramiteDto.getDescripcion());

        tramiteRepository.save(tramite);

        return "redirect:/tramites/saneamiento";
    }

    @GetMapping("/tramites/titulacion")
    public String getListOfDocsTitulacion(Model model) {
        var tramites = tramiteRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("tramites", tramites);
        return "/tramites/titulacion";
    }


    @GetMapping("tramites/titulacionCrear")
    public String createDocTitulacion(Model model){
        TramiteDto tramiteDto = new TramiteDto();
        model.addAttribute("tramiteDto", tramiteDto);
        return "tramites/titulacionCrear";
    }

    @PostMapping("tramites/titulacionCrear")
    public String createDocTitulacion(
            @Valid @ModelAttribute TramiteDto tramiteDto,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("tramiteDto", tramiteDto);  // volver a enviar el DTO con errores
            return "/tramites/titulacionCrear";  // volver a la vista del formulario
        }

        Tramite tramite = new Tramite();
        tramite.setNombre(tramiteDto.getNombre());
        tramite.setDescripcion(tramiteDto.getDescripcion());

        tramiteRepository.save(tramite);

        return "redirect:/tramites/titulacion";
    }
}