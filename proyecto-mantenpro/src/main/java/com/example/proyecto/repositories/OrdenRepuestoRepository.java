package com.example.proyecto.repositories;

import com.example.proyecto.entities.OrdenRepuesto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepuestoRepository extends JpaRepository<OrdenRepuesto, Long> {
    
}