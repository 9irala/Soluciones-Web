package com.example.proyecto.service;

import com.example.proyecto.entities.Maquina;
import com.example.proyecto.repositories.MaquinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaquinaService {

    private final MaquinaRepository maquinaRepository;

    public MaquinaService(MaquinaRepository maquinaRepository) {
        this.maquinaRepository = maquinaRepository;
    }

    public List<Maquina> listarTodas() {
        return maquinaRepository.findAll();
    }

    public Optional<Maquina> buscarPorId(Long id) {
        return maquinaRepository.findById(id);
    }

    public Maquina guardar(Maquina maquina) {
        return maquinaRepository.save(maquina);
    }

    public Maquina actualizar(Long id, Maquina maquina) {

        Maquina existente = maquinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Máquina no encontrada"));

        existente.setNombre(maquina.getNombre());
        existente.setModelo(maquina.getModelo());
        existente.setEstado(maquina.getEstado());

        return maquinaRepository.save(existente);
    }

    public void eliminar(Long id) {
        maquinaRepository.deleteById(id);
    }
}