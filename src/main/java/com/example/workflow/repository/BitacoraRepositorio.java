package com.example.workflow.repository;

import com.example.workflow.entity.Bitacora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BitacoraRepositorio extends JpaRepository<Bitacora, Long> {
    public List<Bitacora>findAll();
}
