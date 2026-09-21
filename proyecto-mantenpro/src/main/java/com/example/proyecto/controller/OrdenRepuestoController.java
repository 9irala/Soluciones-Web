package com.example.proyecto.controller;

import com.example.proyecto.entities.OrdenRepuesto;
import com.example.proyecto.exception.ResourceNotFoundException;
import com.example.proyecto.service.OrdenRepuestoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orden-repuestos")
public class OrdenRepuestoController {

    private final OrdenRepuestoService ordenRepuestoService;

    public OrdenRepuestoController(
            OrdenRepuestoService ordenRepuestoService) {
        this.ordenRepuestoService = ordenRepuestoService;
    }

    @GetMapping
    public ResponseEntity<List<OrdenRepuesto>> listarTodos() {
        return ResponseEntity.ok(ordenRepuestoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenRepuesto> buscarPorId(@PathVariable Long id) {
        OrdenRepuesto ordenRepuesto = ordenRepuestoService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de repuesto no encontrado con id: " + id));
        return ResponseEntity.ok(ordenRepuesto);
    }

    @PostMapping
    public ResponseEntity<OrdenRepuesto> guardar(@Valid @RequestBody OrdenRepuesto ordenRepuesto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenRepuestoService.guardar(ordenRepuesto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenRepuesto> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody OrdenRepuesto ordenRepuesto) {
        return ResponseEntity.ok(ordenRepuestoService.actualizar(id, ordenRepuesto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ordenRepuestoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
