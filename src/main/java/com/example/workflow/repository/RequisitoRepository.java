package com.example.workflow.repository;

import com.example.workflow.entity.Requisito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RequisitoRepository extends JpaRepository<Requisito, Long> {
}
