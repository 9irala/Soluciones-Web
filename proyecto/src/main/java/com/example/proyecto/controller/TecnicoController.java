package com.example.proyecto.controller;

import com.example.proyecto.entities.Tecnico;
import com.example.proyecto.service.TecnicoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tecnicos")
public class TecnicoController {


    private final TecnicoService tecnicoService;

    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @GetMapping
    public ResponseEntity<List<Tecnico>> listarTodos() {
        return ResponseEntity.ok(tecnicoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Tecnico>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tecnicoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Tecnico> guardar(@RequestBody Tecnico tecnico) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tecnicoService.guardar(tecnico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tecnico> actualizar(@PathVariable Long id, @RequestBody Tecnico tecnico) {
        return ResponseEntity.ok(tecnicoService.actualizar(id, tecnico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tecnicoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
