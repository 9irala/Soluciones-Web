package com.example.proyecto.service;

import com.example.proyecto.exception.ResourceNotFoundException;
import com.example.proyecto.entities.Comentario;
import com.example.proyecto.entities.OrdenMantenimiento;
import com.example.proyecto.entities.Usuario;
import com.example.proyecto.repositories.ComentarioRepository;
import com.example.proyecto.repositories.OrdenMantenimientoRepository;
import com.example.proyecto.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final OrdenMantenimientoRepository ordenRepository;
    private final UsuarioRepository usuarioRepository;

    public ComentarioService(
            ComentarioRepository comentarioRepository,
            OrdenMantenimientoRepository ordenRepository,
            UsuarioRepository usuarioRepository) {

        this.comentarioRepository = comentarioRepository;
        this.ordenRepository = ordenRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Comentario> listarTodos() {
        return comentarioRepository.findAll();
    }

    public Optional<Comentario> buscarPorId(Long id) {
        return comentarioRepository.findById(id);
    }

    public Comentario guardar(Comentario comentario) {

        Long ordenId = comentario.getOrden().getId();
        Long usuarioId = comentario.getUsuario().getId();

        OrdenMantenimiento orden = ordenRepository.findById(ordenId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Orden de mantenimiento no encontrada"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"));

        comentario.setOrden(orden);
        comentario.setUsuario(usuario);

        return comentarioRepository.save(comentario);
    }

    public Comentario actualizar(Long id, Comentario comentario) {

        Comentario existente = comentarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Comentario no encontrado"));

        Long ordenId = comentario.getOrden().getId();
        Long usuarioId = comentario.getUsuario().getId();

        OrdenMantenimiento orden = ordenRepository.findById(ordenId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Orden de mantenimiento no encontrada"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"));

        existente.setTexto(comentario.getTexto());
        existente.setFechaHora(comentario.getFechaHora());
        existente.setOrden(orden);
        existente.setUsuario(usuario);

        return comentarioRepository.save(existente);
    }

    public void eliminar(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Comentario no encontrado con id: " + id);
        }
        comentarioRepository.deleteById(id);
    }
}