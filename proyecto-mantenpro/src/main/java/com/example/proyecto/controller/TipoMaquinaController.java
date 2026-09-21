package com.example.proyecto.controller;

import com.example.proyecto.entities.TipoMaquina;
import com.example.proyecto.exception.ResourceNotFoundException;
import com.example.proyecto.service.TipoMaquinaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-maquina")
public class TipoMaquinaController {

    private final TipoMaquinaService tipoMaquinaService;

    public TipoMaquinaController(TipoMaquinaService tipoMaquinaService) {
        this.tipoMaquinaService = tipoMaquinaService;
    }

    @GetMapping
    public ResponseEntity<List<TipoMaquina>> listarTodos() {
        return ResponseEntity.ok(tipoMaquinaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoMaquina> buscarPorId(@PathVariable Long id) {
        TipoMaquina tipoMaquina = tipoMaquinaService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de máquina no encontrado con id: " + id));
        return ResponseEntity.ok(tipoMaquina);
    }

    @PostMapping
    public ResponseEntity<TipoMaquina> guardar(@Valid @RequestBody TipoMaquina tipoMaquina) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoMaquinaService.guardar(tipoMaquina));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoMaquina> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody TipoMaquina tipoMaquina) {
        return ResponseEntity.ok(tipoMaquinaService.actualizar(id, tipoMaquina));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tipoMaquinaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
