package com.example.proyecto.service;

import com.example.proyecto.entities.OrdenMantenimiento;
import com.example.proyecto.entities.Maquina;
import com.example.proyecto.entities.Tecnico;
import com.example.proyecto.repositories.OrdenMantenimientoRepository;
import com.example.proyecto.repositories.MaquinaRepository;
import com.example.proyecto.repositories.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenMantenimientoService {

    private final OrdenMantenimientoRepository ordenRepository;
    private final MaquinaRepository maquinaRepository;
    private final TecnicoRepository tecnicoRepository;

    public OrdenMantenimientoService(
            OrdenMantenimientoRepository ordenRepository,
            MaquinaRepository maquinaRepository,
            TecnicoRepository tecnicoRepository) {

        this.ordenRepository = ordenRepository;
        this.maquinaRepository = maquinaRepository;
        this.tecnicoRepository = tecnicoRepository;
    }

    public List<OrdenMantenimiento> listarTodas() {
        return ordenRepository.findAll();
    }

    public Optional<OrdenMantenimiento> buscarPorId(Long id) {
        return ordenRepository.findById(id);
    }

    public OrdenMantenimiento guardar(OrdenMantenimiento orden) {

        Long maquinaId = orden.getMaquina().getId();
        Long tecnicoId = orden.getTecnico().getId();

        Maquina maquina = maquinaRepository.findById(maquinaId)
                .orElseThrow(() ->
                        new RuntimeException("Máquina no encontrada"));

        Tecnico tecnico = tecnicoRepository.findById(tecnicoId)
                .orElseThrow(() ->
                        new RuntimeException("Técnico no encontrado"));

        orden.setMaquina(maquina);
        orden.setTecnico(tecnico);

        return ordenRepository.save(orden);
    }

    public OrdenMantenimiento actualizar(
            Long id,
            OrdenMantenimiento orden) {

        OrdenMantenimiento existente =
                ordenRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Orden de mantenimiento no encontrada"));

        Long maquinaId = orden.getMaquina().getId();
        Long tecnicoId = orden.getTecnico().getId();

        Maquina maquina = maquinaRepository.findById(maquinaId)
                .orElseThrow(() ->
                        new RuntimeException("Máquina no encontrada"));

        Tecnico tecnico = tecnicoRepository.findById(tecnicoId)
                .orElseThrow(() ->
                        new RuntimeException("Técnico no encontrado"));

        existente.setTipo(orden.getTipo());
        existente.setDescripcion(orden.getDescripcion());
        existente.setFechaProgramada(orden.getFechaProgramada());
        existente.setFechaRealizada(orden.getFechaRealizada());
        existente.setEstado(orden.getEstado());
        existente.setMaquina(maquina);
        existente.setTecnico(tecnico);

        return ordenRepository.save(existente);
    }

    public void eliminar(Long id) {
        ordenRepository.deleteById(id);
    }
}