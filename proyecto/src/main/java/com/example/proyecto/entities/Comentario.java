package com.example.proyecto.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;

    private LocalDateTime fechaHora;

    @ManyToOne
    @JoinColumn(name = "orden_id", nullable = false)
    private OrdenMantenimiento orden;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Comentario() {
    }

    public Comentario(Long id, String texto, LocalDateTime fechaHora,
                      OrdenMantenimiento orden, Usuario usuario) {
        this.id = id;
        this.texto = texto;
        this.fechaHora = fechaHora;
        this.orden = orden;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public OrdenMantenimiento getOrden() {
        return orden;
    }

    public void setOrden(OrdenMantenimiento orden) {
        this.orden = orden;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}