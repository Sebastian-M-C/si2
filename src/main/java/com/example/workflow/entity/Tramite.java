package com.example.workflow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "tramite")
@AllArgsConstructor
@NoArgsConstructor

public class Tramite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String descripcion;

    //atributos para reportes
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    private EstadoTramite estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = true)
    private Cliente cliente;

    @OneToMany(mappedBy = "tramite")
    private Set<ClienteTramite> clienteTramites;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")  // FK en la tabla 'tramite'
    private Categoria categoria;

    @OneToMany(mappedBy = "tramite", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Requisito> requisitos;
}
