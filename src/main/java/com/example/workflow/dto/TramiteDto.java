package com.example.workflow.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TramiteDto {


    @NotEmpty(message = "El nombre es necesario")
    public String nombre;
    @NotEmpty(message = "La descripcion es necesario")
    public String descripcion;



}
