package com.example.workflow.repository;

import com.example.workflow.entity.MonitoreoPlazos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitoreoPlazosRepository extends JpaRepository<MonitoreoPlazos, Long> {
    List<MonitoreoPlazos> findByClienteId(Long clienteId);
    List<MonitoreoPlazos> findByTramiteId(Long tramiteId);
}
