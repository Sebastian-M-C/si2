package com.example.workflow.controller;


import com.example.workflow.dto.ClienteDto;
import com.example.workflow.dto.TramiteDto;
import com.example.workflow.entity.Categoria;
import com.example.workflow.entity.Cliente;
import com.example.workflow.entity.Tramite;
import com.example.workflow.repository.CategoriaRepository;
import com.example.workflow.repository.TramiteRepository;
import com.example.workflow.service.TramiteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TramiteController {

    @Autowired
    public TramiteService tramiteService;

    @Autowired
    public TramiteRepository tramiteRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

//------------------------------------------------------------------------------------------------

    @GetMapping("/tramites/catastro")
    public String getListOfDocsCatastro(Model model) {
        // Obtener la categoría "catastro" (id = 1)
        Long categoriaId = 1L;

        // Encontrar todos los trámites que pertenecen a la categoría con id 1 (catastro)
        List<Tramite> tramites = tramiteRepository.findByCategoriaId(categoriaId);

        // Añadir los trámites al modelo
        model.addAttribute("tramites", tramites);

        // Retornar la vista de la lista de trámites de catastro
        return "/tramites/catastro";
    }


    @GetMapping("tramites/crear")
    public String createDocCatastro(Model model) {
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

        // Crear la entidad Tramite
        Tramite tramite = new Tramite();
        tramite.setNombre(tramiteDto.getNombre());
        tramite.setDescripcion(tramiteDto.getDescripcion());

        // Asignar la categoría usando el id de categoría
        if (tramiteDto.getCategoria_id() != null) {
            Categoria categoria = categoriaRepository.findById(tramiteDto.getCategoria_id())
                    .orElseThrow(() -> new IllegalArgumentException("Categoría no válida"));
            tramite.setCategoria(categoria);
        } else {
            // Si categoria_id es null, puedes manejarlo como desees.
            Categoria categoriaPorDefecto = categoriaRepository.findById(1L) // Asegúrate de que exista
                    .orElseThrow(() -> new IllegalArgumentException("Categoría por defecto no válida"));
            tramite.setCategoria(categoriaPorDefecto);
        }

        // Utiliza el servicio para guardar el Tramite
        tramiteService.saveTramite(tramite);

        return "redirect:/tramites/catastro";
    }

//    -----------------------------------------------------------------------------------------------

    @GetMapping("/tramites/juridica")
    public String getListOfDocsJuridica(Model model) {
        // Obtener la categoría "catastro" (id = 1)
        Long categoriaId = 4L;

        // Encontrar todos los trámites que pertenecen a la categoría con id 1 (catastro)
        List<Tramite> tramites = tramiteRepository.findByCategoriaId(categoriaId);

        // Añadir los trámites al modelo
        model.addAttribute("tramites", tramites);

        // Retornar la vista de la lista de trámites de catastro
        return "/tramites/juridica";
    }

    @GetMapping("tramites/juridicaCrear")
    public String createDocJuridica(Model model) {
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

        // Crear la entidad Tramite
        Tramite tramite = new Tramite();
        tramite.setNombre(tramiteDto.getNombre());
        tramite.setDescripcion(tramiteDto.getDescripcion());

        // Asignar la categoría usando el id de categoría
        if (tramiteDto.getCategoria_id() != null) {
            Categoria categoria = categoriaRepository.findById(tramiteDto.getCategoria_id())
                    .orElseThrow(() -> new IllegalArgumentException("Categoría no válida"));
            tramite.setCategoria(categoria);
        } else {
            // Si categoria_id es null, puedes manejarlo como desees.
            Categoria categoriaPorDefecto = categoriaRepository.findById(4L) // Asegúrate de que exista
                    .orElseThrow(() -> new IllegalArgumentException("Categoría por defecto no válida"));
            tramite.setCategoria(categoriaPorDefecto);
        }

        // Utiliza el servicio para guardar el Tramite
        tramiteService.saveTramite(tramite);

        return "redirect:/tramites/juridica";
    }

//    ------------------------------------------------------------------------------------------

    @GetMapping("/tramites/saneamiento")
    public String getListOfDocsSaneamiento(Model model) {
        // Obtener la categoría "catastro" (id = 1)
        Long categoriaId = 2L;

        // Encontrar todos los trámites que pertenecen a la categoría con id 1 (catastro)
        List<Tramite> tramites = tramiteRepository.findByCategoriaId(categoriaId);

        // Añadir los trámites al modelo
        model.addAttribute("tramites", tramites);

        // Retornar la vista de la lista de trámites de catastro
        return "/tramites/saneamiento";
    }

    @GetMapping("tramites/saneamientoCrear")
    public String createDocSaneamiento(Model model) {
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

        // Crear la entidad Tramite
        Tramite tramite = new Tramite();
        tramite.setNombre(tramiteDto.getNombre());
        tramite.setDescripcion(tramiteDto.getDescripcion());

        // Asignar la categoría usando el id de categoría
        if (tramiteDto.getCategoria_id() != null) {
            Categoria categoria = categoriaRepository.findById(tramiteDto.getCategoria_id())
                    .orElseThrow(() -> new IllegalArgumentException("Categoría no válida"));
            tramite.setCategoria(categoria);
        } else {
            // Si categoria_id es null, puedes manejarlo como desees.
            Categoria categoriaPorDefecto = categoriaRepository.findById(2L) // Asegúrate de que exista
                    .orElseThrow(() -> new IllegalArgumentException("Categoría por defecto no válida"));
            tramite.setCategoria(categoriaPorDefecto);
        }

        // Utiliza el servicio para guardar el Tramite
        tramiteService.saveTramite(tramite);

        return "redirect:/tramites/saneamiento";
    }

//    ------------------------------------------------------------------------------

    @GetMapping("/tramites/titulacion")
    public String getListOfDocsTitulacion(Model model) {
        // Obtener la categoría "catastro" (id = 3)
        Long categoriaId = 3L;

        // Encontrar todos los trámites que pertenecen a la categoría con id 1 (catastro)
        List<Tramite> tramites = tramiteRepository.findByCategoriaId(categoriaId);

        // Añadir los trámites al modelo
        model.addAttribute("tramites", tramites);

        // Retornar la vista de la lista de trámites de catastro
        return "/tramites/titulacion";
    }

    @GetMapping("tramites/titulacionCrear")
    public String createDocTitulacion(Model model) {
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

        // Crear la entidad Tramite
        Tramite tramite = new Tramite();
        tramite.setNombre(tramiteDto.getNombre());
        tramite.setDescripcion(tramiteDto.getDescripcion());

        // Asignar la categoría con el id 2
        Categoria categoria = categoriaRepository.findById(3L)  // Asegúrate de que existe
                .orElseThrow(() -> new IllegalArgumentException("Categoría no válida"));
        tramite.setCategoria(categoria);

        // Utiliza el servicio para guardar el Tramite
        tramiteService.saveTramite(tramite);

        return "redirect:/tramites/titulacion";  // Redirige a la lista de trámites jurídicos
    }
//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/tramites/editarCatastro/{id}")
    public String editarTramiteCatastro(Model model, @PathVariable Long id) {
        Tramite tramite = tramiteRepository.findById(id).orElse(null);
        if (tramite == null) {
            return "redirect:/tramites/catastro";
        }

        TramiteDto tramiteDto = new TramiteDto();
        tramiteDto.setNombre(tramite.getNombre());
        tramiteDto.setDescripcion(tramite.getDescripcion());
        tramiteDto.setCategoria_id(tramite.getCategoria().getId());

        model.addAttribute("tramite", tramite);
        model.addAttribute("tramiteDto", tramiteDto);

        return "/tramites/editarCatastro";
    }

    @PostMapping("/tramites/editarCatastro/{id}")
    public String editarTramiteCatastro(
            Model model,
            @PathVariable Long id,
            @Valid @ModelAttribute TramiteDto tramiteDto,
            BindingResult result
    ) {
        Tramite tramite = tramiteRepository.findById(id).orElse(null);
        if (tramite == null) {
            return "redirect:/tramites/catastro";
        }

        if (result.hasErrors()) {
            model.addAttribute("tramite", tramite);
            return "/tramites/editarCatastro";
        }

        tramite.setNombre(tramiteDto.getNombre());
        tramite.setDescripcion(tramiteDto.getDescripcion());

        // Buscar la categoría por su ID y asignarla al trámite
        Categoria categoria = categoriaRepository.findById(tramiteDto.getCategoria_id()).orElse(null);
        if (categoria != null) {
            tramite.setCategoria(categoria);
        }

        try {
            tramiteRepository.save(tramite);
        } catch (Exception ex) {
            return "/tramites/editarCatastro";
        }

        return "redirect:/tramites/catastro";
    }

//--------------------------------------------------------------------------------------------------------

    @GetMapping("/tramites/editarJuridica/{id}")
    public String editarTramiteJuridica(Model model, @PathVariable Long id) {
        Tramite tramite = tramiteRepository.findById(id).orElse(null);
        if (tramite == null) {
            return "redirect:/tramites/juridica";
        }

        TramiteDto tramiteDto = new TramiteDto();
        tramiteDto.setNombre(tramite.getNombre());
        tramiteDto.setDescripcion(tramite.getDescripcion());
        tramiteDto.setCategoria_id(tramite.getCategoria().getId());

        model.addAttribute("tramite", tramite);
        model.addAttribute("tramiteDto", tramiteDto);

        return "/tramites/editarJuridica";
    }

    @PostMapping("/tramites/editarJuridica/{id}")
    public String editarTramiteJuridica(
            Model model,
            @PathVariable Long id,
            @Valid @ModelAttribute TramiteDto tramiteDto,
            BindingResult result
    ) {
        Tramite tramite = tramiteRepository.findById(id).orElse(null);
        if (tramite == null) {
            return "redirect:/tramites/juridica";
        }

        if (result.hasErrors()) {
            model.addAttribute("tramite", tramite);
            return "/tramites/editarJuridica";
        }

        tramite.setNombre(tramiteDto.getNombre());
        tramite.setDescripcion(tramiteDto.getDescripcion());

        // Buscar la categoría por su ID y asignarla al trámite
        Categoria categoria = categoriaRepository.findById(tramiteDto.getCategoria_id()).orElse(null);
        if (categoria != null) {
            tramite.setCategoria(categoria);
        }

        try {
            tramiteRepository.save(tramite);
        } catch (Exception ex) {
            return "/tramites/editarJuridica";
        }

        return "redirect:/tramites/juridica";
    }

//-----------------------------------------------------------------------------------------------------------------
@GetMapping("/tramites/editarSaneamiento/{id}")
public String editarTramiteSaneamiento(Model model, @PathVariable Long id) {
    Tramite tramite = tramiteRepository.findById(id).orElse(null);
    if (tramite == null) {
        return "redirect:/tramites/saneamiento";
    }

    TramiteDto tramiteDto = new TramiteDto();
    tramiteDto.setNombre(tramite.getNombre());
    tramiteDto.setDescripcion(tramite.getDescripcion());
    tramiteDto.setCategoria_id(tramite.getCategoria().getId());

    model.addAttribute("tramite", tramite);
    model.addAttribute("tramiteDto", tramiteDto);

    return "/tramites/editarSaneamiento";
}

    @PostMapping("/tramites/editarSaneamiento/{id}")
    public String editarTramiteSaneamiento(
            Model model,
            @PathVariable Long id,
            @Valid @ModelAttribute TramiteDto tramiteDto,
            BindingResult result
    ) {
        Tramite tramite = tramiteRepository.findById(id).orElse(null);
        if (tramite == null) {
            return "redirect:/tramites/saneamiento";
        }

        if (result.hasErrors()) {
            model.addAttribute("tramite", tramite);
            return "/tramites/editarSaneamiento";
        }

        tramite.setNombre(tramiteDto.getNombre());
        tramite.setDescripcion(tramiteDto.getDescripcion());

        // Buscar la categoría por su ID y asignarla al trámite
        Categoria categoria = categoriaRepository.findById(tramiteDto.getCategoria_id()).orElse(null);
        if (categoria != null) {
            tramite.setCategoria(categoria);
        }

        try {
            tramiteRepository.save(tramite);
        } catch (Exception ex) {
            return "/tramites/editarSaneamiento";
        }

        return "redirect:/tramites/saneamiento";
    }

//    -----------------------------------------------------------------------------------------------------------------

    @GetMapping("/tramites/editarTitulacion/{id}")
    public String editarTramiteTitulacion(Model model, @PathVariable Long id) {
        Tramite tramite = tramiteRepository.findById(id).orElse(null);
        if (tramite == null) {
            return "redirect:/tramites/titulacion";
        }

        TramiteDto tramiteDto = new TramiteDto();
        tramiteDto.setNombre(tramite.getNombre());
        tramiteDto.setDescripcion(tramite.getDescripcion());
        tramiteDto.setCategoria_id(tramite.getCategoria().getId());

        model.addAttribute("tramite", tramite);
        model.addAttribute("tramiteDto", tramiteDto);

        return "/tramites/editarTitulacion";
    }

    @PostMapping("/tramites/editarTitulacion/{id}")
    public String editarTramiteTitulacion(
            Model model,
            @PathVariable Long id,
            @Valid @ModelAttribute TramiteDto tramiteDto,
            BindingResult result
    ) {
        Tramite tramite = tramiteRepository.findById(id).orElse(null);
        if (tramite == null) {
            return "redirect:/tramites/titulacion";
        }

        if (result.hasErrors()) {
            model.addAttribute("tramite", tramite);
            return "/tramites/editarTitulacion";
        }

        tramite.setNombre(tramiteDto.getNombre());
        tramite.setDescripcion(tramiteDto.getDescripcion());

        // Buscar la categoría por su ID y asignarla al trámite
        Categoria categoria = categoriaRepository.findById(tramiteDto.getCategoria_id()).orElse(null);
        if (categoria != null) {
            tramite.setCategoria(categoria);
        }

        try {
            tramiteRepository.save(tramite);
        } catch (Exception ex) {
            return "/tramites/editarTitulacion";
        }

        return "redirect:/tramites/titulacion";
    }

//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/tramites/catastro/eliminar")
        public String eliminarTramiteCatastro(@RequestParam Long id) {
        Tramite tramite = tramiteRepository.findById(id).orElse(null);

        if (tramite != null) {
            tramiteRepository.delete(tramite);
        }
        return "redirect:/tramites/catastro";
    }

    //-----------------------------------------------------------------------------------------------

    @GetMapping("/tramites/saneamiento/eliminar")
        public String eliminarTramiteSaneamiento(@RequestParam Long id) {
            Tramite tramite = tramiteRepository.findById(id).orElse(null);

            if (tramite != null) {
                tramiteRepository.delete(tramite);
             }
         return "redirect:/tramites/saneamiento";
    }

//    --------------------------------------------------------------------------------------------

    @GetMapping("/tramites/titulacion/eliminar")
    public String eliminarTramiteTitulacion(@RequestParam Long id) {
        Tramite tramite = tramiteRepository.findById(id).orElse(null);

        if (tramite != null) {
            tramiteRepository.delete(tramite);
        }
        return "redirect:/tramites/titulacion";
    }

//    ---------------------------------------------------------------------------------------------------------

    @GetMapping("/tramites/juridica/eliminar")
    public String eliminarTramiteJuridica(@RequestParam Long id) {
        Tramite tramite = tramiteRepository.findById(id).orElse(null);

        if (tramite != null) {
            tramiteRepository.delete(tramite);
        }
        return "redirect:/tramites/juridica";
    }

}




