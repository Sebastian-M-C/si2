package com.example.workflow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteDto {

    @NotEmpty(message = "El nombre se requiere ingresar")
    private String nombre;

    @NotEmpty(message = "El apellido se requiere ingresar")
    private String apellido;

    @NotEmpty(message = "El correo es necesario ingresar")
    @Email
    private String email;

    @NotEmpty(message = "El estado es necesario")
    private String status;


    public ClienteDto(String nombre, String apellido, String email, String status) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.status = status;
    }

    public ClienteDto() {
    }

}
