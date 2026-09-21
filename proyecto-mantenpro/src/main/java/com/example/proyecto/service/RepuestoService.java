package com.example.proyecto.service;

import com.example.proyecto.exception.ResourceNotFoundException;
import com.example.proyecto.entities.Repuesto;
import com.example.proyecto.entities.Proveedor;
import com.example.proyecto.repositories.RepuestoRepository;
import com.example.proyecto.repositories.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepuestoService {

    private final RepuestoRepository repuestoRepository;
    private final ProveedorRepository proveedorRepository;

    public RepuestoService(
            RepuestoRepository repuestoRepository,
            ProveedorRepository proveedorRepository) {

        this.repuestoRepository = repuestoRepository;
        this.proveedorRepository = proveedorRepository;
    }

    public List<Repuesto> listarTodos() {
        return repuestoRepository.findAll();
    }

    public Optional<Repuesto> buscarPorId(Long id) {
        return repuestoRepository.findById(id);
    }

    public Repuesto guardar(Repuesto repuesto) {

        Long proveedorId = repuesto.getProveedor().getId();

        Proveedor proveedor = proveedorRepository.findById(proveedorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Proveedor no encontrado"));

        repuesto.setProveedor(proveedor);

        return repuestoRepository.save(repuesto);
    }

    public Repuesto actualizar(Long id, Repuesto repuesto) {

        Repuesto existente = repuestoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Repuesto no encontrado"));

        Long proveedorId = repuesto.getProveedor().getId();

        Proveedor proveedor = proveedorRepository.findById(proveedorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Proveedor no encontrado"));

        existente.setNombre(repuesto.getNombre());
        existente.setCodigo(repuesto.getCodigo());
        existente.setStock(repuesto.getStock());
        existente.setPrecioUnitario(repuesto.getPrecioUnitario());
        existente.setProveedor(proveedor);

        return repuestoRepository.save(existente);
    }

    public void eliminar(Long id) {
        if (!repuestoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Repuesto no encontrado con id: " + id);
        }
        repuestoRepository.deleteById(id);
    }
}