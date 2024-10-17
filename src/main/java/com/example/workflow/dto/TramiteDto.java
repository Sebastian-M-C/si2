package com.example.workflow.dto;

import com.example.workflow.entity.Cliente;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.Getter;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TramiteDto {

    @NotEmpty(message = "El nombre es necesario")
    public String nombre;
    @NotEmpty(message = "La descripcion es necesario")
    public String descripcion;
    private Long categoria_id;
    @Column(nullable = true)
    private Cliente cliente_id;
}
