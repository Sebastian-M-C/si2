package com.example.workflow.repository;

import com.example.workflow.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public Cliente findByEmail(String email);
}

