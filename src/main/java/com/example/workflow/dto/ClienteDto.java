package com.example.workflow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
