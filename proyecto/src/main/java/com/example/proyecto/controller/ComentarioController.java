package com.example.proyecto.controller;

import com.example.proyecto.entities.Comentario;
import com.example.proyecto.service.ComentarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping
    public List<Comentario> listarTodos() {
        return comentarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Comentario> buscarPorId(@PathVariable Long id) {
        return comentarioService.buscarPorId(id);
    }

    @PostMapping
    public Comentario guardar(@RequestBody Comentario comentario) {
        return comentarioService.guardar(comentario);
    }

    @PutMapping("/{id}")
    public Comentario actualizar(
            @PathVariable Long id,
            @RequestBody Comentario comentario) {

        return comentarioService.actualizar(id, comentario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        comentarioService.eliminar(id);
    }
}