package com.example.proyecto.controller;

import com.example.proyecto.entities.OrdenMantenimiento;
import com.example.proyecto.exception.ResourceNotFoundException;
import com.example.proyecto.service.OrdenMantenimientoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenMantenimientoController {

    private final OrdenMantenimientoService ordenService;

    public OrdenMantenimientoController(
            OrdenMantenimientoService ordenService) {
        this.ordenService = ordenService;
    }

    @GetMapping
    public ResponseEntity<List<OrdenMantenimiento>> listarTodas() {
        return ResponseEntity.ok(ordenService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenMantenimiento> buscarPorId(@PathVariable Long id) {
        OrdenMantenimiento orden = ordenService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Orden de mantenimiento no encontrada con id: " + id));
        return ResponseEntity.ok(orden);
    }

    @PostMapping
    public ResponseEntity<OrdenMantenimiento> guardar(@Valid @RequestBody OrdenMantenimiento orden) {
        OrdenMantenimiento creada = ordenService.guardar(orden);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenMantenimiento> actualizar(@PathVariable Long id, @Valid @RequestBody OrdenMantenimiento orden) {
        return ResponseEntity.ok(ordenService.actualizar(id, orden));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ordenService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
