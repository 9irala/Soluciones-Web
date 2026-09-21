package com.example.proyecto.service;

import com.example.proyecto.exception.ResourceNotFoundException;
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

        if (orden.getMaquina() == null || orden.getMaquina().getId() == null) {
            throw new IllegalArgumentException("Debe indicar el id de la máquina (maquina.id)");
        }
        if (orden.getTecnico() == null || orden.getTecnico().getId() == null) {
            throw new IllegalArgumentException("Debe indicar el id del técnico (tecnico.id)");
        }

        Long maquinaId = orden.getMaquina().getId();
        Long tecnicoId = orden.getTecnico().getId();

        Maquina maquina = maquinaRepository.findById(maquinaId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Máquina no encontrada con id: " + maquinaId));

        Tecnico tecnico = tecnicoRepository.findById(tecnicoId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Técnico no encontrado con id: " + tecnicoId));

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
                                new ResourceNotFoundException(
                                        "Orden de mantenimiento no encontrada con id: " + id));

        if (orden.getMaquina() == null || orden.getMaquina().getId() == null) {
            throw new IllegalArgumentException("Debe indicar el id de la máquina (maquina.id)");
        }
        if (orden.getTecnico() == null || orden.getTecnico().getId() == null) {
            throw new IllegalArgumentException("Debe indicar el id del técnico (tecnico.id)");
        }

        Long maquinaId = orden.getMaquina().getId();
        Long tecnicoId = orden.getTecnico().getId();

        Maquina maquina = maquinaRepository.findById(maquinaId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Máquina no encontrada con id: " + maquinaId));

        Tecnico tecnico = tecnicoRepository.findById(tecnicoId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Técnico no encontrado con id: " + tecnicoId));

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
        if (!ordenRepository.existsById(id)) {
            throw new ResourceNotFoundException("Orden de mantenimiento no encontrada con id: " + id);
        }
        ordenRepository.deleteById(id);
    }
}
