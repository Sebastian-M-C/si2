package com.example.workflow.repository;

import com.example.workflow.entity.Tramite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TramiteRepository extends JpaRepository<Tramite, Long> {
    List<Tramite> findByCategoriaId(Long categoriaId);
}
