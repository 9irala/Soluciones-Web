package com.example.proyecto.repositories;

import com.example.proyecto.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
