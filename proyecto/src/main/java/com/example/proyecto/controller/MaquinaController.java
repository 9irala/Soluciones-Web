package com.example.proyecto.controller;

import com.example.proyecto.entities.Maquina;
import com.example.proyecto.service.MaquinaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/maquinas")
public class MaquinaController {

    private final MaquinaService maquinaService;

    public MaquinaController(MaquinaService maquinaService) {
        this.maquinaService = maquinaService;
    }

    @GetMapping
    public List<Maquina> listarTodas() {
        return maquinaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Optional<Maquina> buscarPorId(@PathVariable Long id) {
        return maquinaService.buscarPorId(id);
    }

    @PostMapping
    public Maquina guardar(@RequestBody Maquina maquina) {
        return maquinaService.guardar(maquina);
    }

    @PutMapping("/{id}")
    public Maquina actualizar(
            @PathVariable Long id,
            @RequestBody Maquina maquina) {

        return maquinaService.actualizar(id, maquina);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        maquinaService.eliminar(id);
    }
}