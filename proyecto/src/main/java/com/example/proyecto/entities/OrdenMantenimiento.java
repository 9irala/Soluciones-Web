package com.example.proyecto.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ordenes_mantenimiento")
public class OrdenMantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private String descripcion;

    private LocalDate fechaProgramada;

    private LocalDate fechaRealizada;

    private String estado;

    @ManyToOne
    @JoinColumn(name = "maquina_id", nullable = false)
    private Maquina maquina;

    @ManyToOne
    @JoinColumn(name = "tecnico_id", nullable = false)
    private Tecnico tecnico;

    public OrdenMantenimiento() {
    }

    public OrdenMantenimiento(
            Long id,
            String tipo,
            String descripcion,
            LocalDate fechaProgramada,
            LocalDate fechaRealizada,
            String estado,
            Maquina maquina,
            Tecnico tecnico) {

        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fechaProgramada = fechaProgramada;
        this.fechaRealizada = fechaRealizada;
        this.estado = estado;
        this.maquina = maquina;
        this.tecnico = tecnico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(LocalDate fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public LocalDate getFechaRealizada() {
        return fechaRealizada;
    }

    public void setFechaRealizada(LocalDate fechaRealizada) {
        this.fechaRealizada = fechaRealizada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }
}