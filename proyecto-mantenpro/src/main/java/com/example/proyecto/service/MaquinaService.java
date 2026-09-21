package com.example.proyecto.service;

import com.example.proyecto.exception.ResourceNotFoundException;
import com.example.proyecto.entities.Maquina;
import com.example.proyecto.entities.TipoMaquina;
import com.example.proyecto.repositories.MaquinaRepository;
import com.example.proyecto.repositories.TipoMaquinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaquinaService {

    private final MaquinaRepository maquinaRepository;
    private final TipoMaquinaRepository tipoMaquinaRepository;

    public MaquinaService(
            MaquinaRepository maquinaRepository,
            TipoMaquinaRepository tipoMaquinaRepository) {

        this.maquinaRepository = maquinaRepository;
        this.tipoMaquinaRepository = tipoMaquinaRepository;
    }

    public List<Maquina> listarTodas() {
        return maquinaRepository.findAll();
    }

    public Optional<Maquina> buscarPorId(Long id) {
        return maquinaRepository.findById(id);
    }

    public Maquina guardar(Maquina maquina) {

        Long tipoId = maquina.getTipoMaquina().getId();

        TipoMaquina tipo = tipoMaquinaRepository.findById(tipoId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Tipo de máquina no encontrado"));

        maquina.setTipoMaquina(tipo);

        return maquinaRepository.save(maquina);
    }

    public Maquina actualizar(Long id, Maquina maquina) {

        Maquina existente = maquinaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Máquina no encontrada"));

        Long tipoId = maquina.getTipoMaquina().getId();

        TipoMaquina tipo = tipoMaquinaRepository.findById(tipoId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Tipo de máquina no encontrado"));

        existente.setNombre(maquina.getNombre());
        existente.setModelo(maquina.getModelo());
        existente.setNumeroSerie(maquina.getNumeroSerie());
        existente.setFechaCompra(maquina.getFechaCompra());
        existente.setEstado(maquina.getEstado());
        existente.setTipoMaquina(tipo);

        return maquinaRepository.save(existente);
    }

    public void eliminar(Long id) {
        if (!maquinaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Máquina no encontrado con id: " + id);
        }
        maquinaRepository.deleteById(id);
    }
}