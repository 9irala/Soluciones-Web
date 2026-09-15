package com.example.proyecto.controller;

import com.example.proyecto.entities.Repuesto;
import com.example.proyecto.service.RepuestoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/repuestos")
public class RepuestoController {

    private final RepuestoService repuestoService;

    public RepuestoController(RepuestoService repuestoService) {
        this.repuestoService = repuestoService;
    }

    @GetMapping
    public List<Repuesto> listarTodos() {
        return repuestoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Repuesto> buscarPorId(@PathVariable Long id) {
        return repuestoService.buscarPorId(id);
    }

    @PostMapping
    public Repuesto guardar(@RequestBody Repuesto repuesto) {
        return repuestoService.guardar(repuesto);
    }

    @PutMapping("/{id}")
    public Repuesto actualizar(
            @PathVariable Long id,
            @RequestBody Repuesto repuesto) {

        return repuestoService.actualizar(id, repuesto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repuestoService.eliminar(id);
    }

}
