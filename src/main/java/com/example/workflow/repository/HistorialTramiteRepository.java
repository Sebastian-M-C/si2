package com.example.workflow.repository;

import com.example.workflow.entity.HistorialTramite;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialTramiteRepository extends JpaRepository<HistorialTramite, Long> {
}