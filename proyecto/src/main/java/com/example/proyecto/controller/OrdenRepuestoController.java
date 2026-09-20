package com.example.proyecto.controller;

import com.example.proyecto.entities.OrdenRepuesto;
import com.example.proyecto.service.OrdenRepuestoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orden-repuestos")
public class OrdenRepuestoController {

    private final OrdenRepuestoService ordenRepuestoService;

    public OrdenRepuestoController(
            OrdenRepuestoService ordenRepuestoService) {
        this.ordenRepuestoService = ordenRepuestoService;
    }

    @GetMapping
    public List<OrdenRepuesto> listarTodos() {
        return ordenRepuestoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<OrdenRepuesto> buscarPorId(
            @PathVariable Long id) {

        return ordenRepuestoService.buscarPorId(id);
    }

    @PostMapping
    public OrdenRepuesto guardar(
            @RequestBody OrdenRepuesto ordenRepuesto) {

        return ordenRepuestoService.guardar(ordenRepuesto);
    }

    @PutMapping("/{id}")
    public OrdenRepuesto actualizar(
            @PathVariable Long id,
            @RequestBody OrdenRepuesto ordenRepuesto) {

        return ordenRepuestoService.actualizar(id, ordenRepuesto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        ordenRepuestoService.eliminar(id);
    }
}