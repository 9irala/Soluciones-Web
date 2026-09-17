package com.example.proyecto.service;

import com.example.proyecto.entities.TipoMaquina;
import com.example.proyecto.repositories.TipoMaquinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service 
public class TipoMaquinaService {

    private final TipoMaquinaRepository tipoMaquinaRepository;

    public TipoMaquinaService(TipoMaquinaRepository tipoMaquinaRepository) {
        this.tipoMaquinaRepository = tipoMaquinaRepository;
    }

    public List<TipoMaquina> listarTodos() {
        return tipoMaquinaRepository.findAll();
    }

    public Optional<TipoMaquina> buscarPorId(Long id) {
        return tipoMaquinaRepository.findById(id);
    }

    public TipoMaquina guardar(TipoMaquina tipoMaquina) {
        return tipoMaquinaRepository.save(tipoMaquina);
    }

    public TipoMaquina actualizar(Long id, TipoMaquina tipoMaquina) {

        TipoMaquina existente = tipoMaquinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de máquina no encontrado"));

        existente.setNombre(tipoMaquina.getNombre());
        existente.setDescripcion(tipoMaquina.getDescripcion());

        return tipoMaquinaRepository.save(existente);
    }

    public void eliminar(Long id) {
        tipoMaquinaRepository.deleteById(id);
    }
}
