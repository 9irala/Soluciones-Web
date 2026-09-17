package com.example.proyecto.controller;

import com.example.proyecto.entities.TipoMaquina;
import com.example.proyecto.service.TipoMaquinaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipos-maquina")
public class TipoMaquinaController {

     private final TipoMaquinaService tipoMaquinaService;

    public TipoMaquinaController(TipoMaquinaService tipoMaquinaService) {
        this.tipoMaquinaService = tipoMaquinaService;
    }

    @GetMapping
    public List<TipoMaquina> listarTodos() {
        return tipoMaquinaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<TipoMaquina> buscarPorId(@PathVariable Long id) {
        return tipoMaquinaService.buscarPorId(id);
    }

    @PostMapping
    public TipoMaquina guardar(@RequestBody TipoMaquina tipoMaquina) {
        return tipoMaquinaService.guardar(tipoMaquina);
    }

    @PutMapping("/{id}")
    public TipoMaquina actualizar(
            @PathVariable Long id,
            @RequestBody TipoMaquina tipoMaquina) {

        return tipoMaquinaService.actualizar(id, tipoMaquina);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        tipoMaquinaService.eliminar(id);
    }
}
