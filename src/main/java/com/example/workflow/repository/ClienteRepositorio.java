package com.example.workflow.repository;

import com.example.workflow.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepositorio extends JpaRepository<Cliente,Long> {

    public Cliente findByEmail(String email);

}
