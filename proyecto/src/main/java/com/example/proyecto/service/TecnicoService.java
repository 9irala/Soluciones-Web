package com.example.proyecto.service;

import com.example.proyecto.entities.Tecnico;
import com.example.proyecto.repositories.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class TecnicoService {

      private final TecnicoRepository tecnicoRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    public List<Tecnico> listarTodos() {
        return tecnicoRepository.findAll();
    }

    public Optional<Tecnico> buscarPorId(Long id) {
        return tecnicoRepository.findById(id);
    }

    public Tecnico guardar(Tecnico tecnico) {
        return tecnicoRepository.save(tecnico);
    }

    public Tecnico actualizar(Long id, Tecnico tecnico) {

        Tecnico existente = tecnicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Técnico no encontrado"));

        existente.setNombre(tecnico.getNombre());
        existente.setEspecialidad(tecnico.getEspecialidad());
        existente.setTelefono(tecnico.getTelefono());

        return tecnicoRepository.save(existente);
    }

    public void eliminar(Long id) {
        tecnicoRepository.deleteById(id);
    }
}
