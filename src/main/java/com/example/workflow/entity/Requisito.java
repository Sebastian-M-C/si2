package com.example.workflow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@Table(name = "requisito")
@AllArgsConstructor
@NoArgsConstructor
public class Requisito implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String ci;
    private String expedido;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Long celular;
    private String correo;
    private String numeroTitulo;
    private Long deposito;
    private String oficinadeposito;
    private byte[] fotodeposito;
    private byte[] fotofolio;

    @ManyToOne
    @JoinColumn(name = "id_Tramite")
    private Tramite tramite;

}
