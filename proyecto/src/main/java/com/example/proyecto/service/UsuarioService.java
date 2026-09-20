package com.example.proyecto.service;

import com.example.proyecto.entities.Usuario;
import com.example.proyecto.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizar(Long id, Usuario usuario) {

        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        existente.setNombre(usuario.getNombre());
        existente.setEmail(usuario.getEmail());
        existente.setPassword(usuario.getPassword());

        return usuarioRepository.save(existente);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
    
}
