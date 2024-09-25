package com.example.workflow.repository;


import com.example.workflow.entity.ClienteTramite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteTramiteRepository extends JpaRepository<ClienteTramite, Long> {

}

