package com.example.proyecto.controller;

import com.example.proyecto.entities.Tecnico;
import com.example.proyecto.service.TecnicoService;
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
    public List<Tecnico> listarTodos() {
        return tecnicoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Tecnico> buscarPorId(@PathVariable Long id) {
        return tecnicoService.buscarPorId(id);
    }

    @PostMapping
    public Tecnico guardar(@RequestBody Tecnico tecnico) {
        return tecnicoService.guardar(tecnico);
    }

    @PutMapping("/{id}")
    public Tecnico actualizar(
            @PathVariable Long id,
            @RequestBody Tecnico tecnico) {

        return tecnicoService.actualizar(id, tecnico);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        tecnicoService.eliminar(id);
    }

}
