package com.example.proyecto.service;

import com.example.proyecto.entities.Proveedor;
import com.example.proyecto.repositories.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {


    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public List<Proveedor> listarTodos() {
        return proveedorRepository.findAll();
    }

    public Optional<Proveedor> buscarPorId(Long id) {
        return proveedorRepository.findById(id);
    }

    public Proveedor guardar(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public Proveedor actualizar(Long id, Proveedor proveedor) {

        Proveedor existente = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        existente.setNombre(proveedor.getNombre());
        existente.setContacto(proveedor.getContacto());
        existente.setTelefono(proveedor.getTelefono());

        return proveedorRepository.save(existente);
    }

    public void eliminar(Long id) {
        proveedorRepository.deleteById(id);
    }

}
