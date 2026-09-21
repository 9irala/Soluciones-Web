package com.example.proyecto.service;

import com.example.proyecto.exception.ResourceNotFoundException;
import com.example.proyecto.entities.OrdenRepuesto;
import com.example.proyecto.entities.OrdenMantenimiento;
import com.example.proyecto.entities.Repuesto;
import com.example.proyecto.repositories.OrdenRepuestoRepository;
import com.example.proyecto.repositories.OrdenMantenimientoRepository;
import com.example.proyecto.repositories.RepuestoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenRepuestoService {

    private final OrdenRepuestoRepository ordenRepuestoRepository;
    private final OrdenMantenimientoRepository ordenRepository;
    private final RepuestoRepository repuestoRepository;

    public OrdenRepuestoService(
            OrdenRepuestoRepository ordenRepuestoRepository,
            OrdenMantenimientoRepository ordenRepository,
            RepuestoRepository repuestoRepository) {

        this.ordenRepuestoRepository = ordenRepuestoRepository;
        this.ordenRepository = ordenRepository;
        this.repuestoRepository = repuestoRepository;
    }

    public List<OrdenRepuesto> listarTodos() {
        return ordenRepuestoRepository.findAll();
    }

    public Optional<OrdenRepuesto> buscarPorId(Long id) {
        return ordenRepuestoRepository.findById(id);
    }

    public OrdenRepuesto guardar(OrdenRepuesto ordenRepuesto) {

        Long ordenId = ordenRepuesto.getOrden().getId();
        Long repuestoId = ordenRepuesto.getRepuesto().getId();

        OrdenMantenimiento orden = ordenRepository.findById(ordenId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Orden de mantenimiento no encontrada"));

        Repuesto repuesto = repuestoRepository.findById(repuestoId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Repuesto no encontrado"));

        ordenRepuesto.setOrden(orden);
        ordenRepuesto.setRepuesto(repuesto);

        return ordenRepuestoRepository.save(ordenRepuesto);
    }

    public OrdenRepuesto actualizar(
            Long id,
            OrdenRepuesto ordenRepuesto) {

        OrdenRepuesto existente =
                ordenRepuestoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Registro de repuesto no encontrado"));

        Long ordenId = ordenRepuesto.getOrden().getId();
        Long repuestoId = ordenRepuesto.getRepuesto().getId();

        OrdenMantenimiento orden = ordenRepository.findById(ordenId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Orden de mantenimiento no encontrada"));

        Repuesto repuesto = repuestoRepository.findById(repuestoId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Repuesto no encontrado"));

        existente.setCantidad(ordenRepuesto.getCantidad());
        existente.setOrden(orden);
        existente.setRepuesto(repuesto);

        return ordenRepuestoRepository.save(existente);
    }

    public void eliminar(Long id) {
        if (!ordenRepuestoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Registro de repuesto no encontrado con id: " + id);
        }
        ordenRepuestoRepository.deleteById(id);
    }
}