package com.example.proyecto.controller;

import com.example.proyecto.entities.OrdenMantenimiento;
import com.example.proyecto.service.OrdenMantenimientoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenMantenimientoController {

    private final OrdenMantenimientoService ordenService;

    public OrdenMantenimientoController(
            OrdenMantenimientoService ordenService) {
        this.ordenService = ordenService;
    }

    @GetMapping
    public List<OrdenMantenimiento> listarTodas() {
        return ordenService.listarTodas();
    }

    @GetMapping("/{id}")
    public Optional<OrdenMantenimiento> buscarPorId(
            @PathVariable Long id) {

        return ordenService.buscarPorId(id);
    }

    @PostMapping
    public OrdenMantenimiento guardar(
            @RequestBody OrdenMantenimiento orden) {

        return ordenService.guardar(orden);
    }

    @PutMapping("/{id}")
    public OrdenMantenimiento actualizar(
            @PathVariable Long id,
            @RequestBody OrdenMantenimiento orden) {

        return ordenService.actualizar(id, orden);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        ordenService.eliminar(id);
    }
}