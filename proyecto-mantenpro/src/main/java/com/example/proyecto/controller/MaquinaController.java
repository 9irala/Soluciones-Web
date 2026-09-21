package com.example.proyecto.controller;

import com.example.proyecto.entities.Maquina;
import com.example.proyecto.exception.ResourceNotFoundException;
import com.example.proyecto.service.MaquinaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maquinas")
public class MaquinaController {

    private final MaquinaService maquinaService;

    public MaquinaController(MaquinaService maquinaService) {
        this.maquinaService = maquinaService;
    }

    @GetMapping
    public ResponseEntity<List<Maquina>> listarTodas() {
        return ResponseEntity.ok(maquinaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maquina> buscarPorId(@PathVariable Long id) {
        Maquina maquina = maquinaService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Máquina no encontrada con id: " + id));
        return ResponseEntity.ok(maquina);
    }

    @PostMapping
    public ResponseEntity<Maquina> guardar(@Valid @RequestBody Maquina maquina) {
        return ResponseEntity.status(HttpStatus.CREATED).body(maquinaService.guardar(maquina));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maquina> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Maquina maquina) {
        return ResponseEntity.ok(maquinaService.actualizar(id, maquina));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        maquinaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
