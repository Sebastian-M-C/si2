package com.example.workflow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "monitoreo")
public class MonitoreoPlazos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tramite_id", nullable = false)
    private Tramite tramite;

    //fecha inicio
    private LocalDate fechaInicio;
    //fecha de vencimiento
    private LocalDate fechaVencimiento;

    @Enumerated(EnumType.STRING)
    private EstadoTramite estado;

    // Método para verificar si el trámite está vencido
    public boolean isVencido() {
        return fechaVencimiento.isBefore(LocalDate.now());
    }
}
