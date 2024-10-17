package com.example.workflow.repository;

import com.example.workflow.entity.Tramite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface TramiteRepository extends JpaRepository<Tramite, Long> {
    List<Tramite> findByCategoriaId(Long categoriaId);


    @Query("SELECT t FROM Tramite t WHERE (:clienteId IS NULL OR t.cliente.id = :clienteId) "
            + "AND (:categoriaId IS NULL OR t.categoria.id = :categoriaId) "
            + "AND (cast(:fechaInicio as date) IS NULL OR t.fechaInicio >= :fechaInicio) "
            + "AND (cast(:fechaFin as date) IS NULL OR t.fechaFin <= :fechaFin) ")
    List<Tramite> findTramitesByFilters(@Param("clienteId") Long clienteId,
                                        @Param("categoriaId") Long categoriaId,
                                        @Param("fechaInicio") LocalDate fechaInicio,
                                        @Param("fechaFin") LocalDate fechaFin);
}
