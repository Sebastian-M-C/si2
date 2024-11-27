package com.example.workflow.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "historialtramite")
public class HistorialTramite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departamento; // Descripción del departamento

    private LocalDateTime fechaRegistro; // Fecha y hora del registro

//    @Enumerated(EnumType.STRING)
//    private EstadoTramite estado;
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tramite_id", nullable = false)
    private Tramite tramite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public HistorialTramite(Long id, String departamento, LocalDateTime fechaRegistro, String estado, Tramite tramite, Cliente cliente) {
        this.id = id;
        this.departamento = departamento;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.tramite = tramite;
        this.cliente = cliente;
    }

    public HistorialTramite(String departamento, LocalDateTime fechaRegistro, String estado, Tramite tramite, Cliente cliente) {
        this.departamento = departamento;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.tramite = tramite;
        this.cliente = cliente;
    }

    public HistorialTramite() {
    }
}

