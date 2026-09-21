package com.example.proyecto.controller;

import com.example.proyecto.entities.Repuesto;
import com.example.proyecto.exception.ResourceNotFoundException;
import com.example.proyecto.service.RepuestoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repuestos")
public class RepuestoController {

    private final RepuestoService repuestoService;

    public RepuestoController(RepuestoService repuestoService) {
        this.repuestoService = repuestoService;
    }

    @GetMapping
    public ResponseEntity<List<Repuesto>> listarTodos() {
        return ResponseEntity.ok(repuestoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repuesto> buscarPorId(@PathVariable Long id) {
        Repuesto repuesto = repuestoService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Repuesto no encontrado con id: " + id));
        return ResponseEntity.ok(repuesto);
    }

    @PostMapping
    public ResponseEntity<Repuesto> guardar(@Valid @RequestBody Repuesto repuesto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repuestoService.guardar(repuesto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repuesto> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Repuesto repuesto) {
        return ResponseEntity.ok(repuestoService.actualizar(id, repuesto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        repuestoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
