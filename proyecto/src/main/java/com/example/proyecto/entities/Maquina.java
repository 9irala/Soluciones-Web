package com.example.proyecto.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "maquinas")
public class Maquina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String modelo;

    private String numeroSerie;

    private LocalDate fechaCompra;

    private String estado;

    @ManyToOne
    @JoinColumn(name = "tipo_maquina_id", nullable = false)
    private TipoMaquina tipoMaquina;

    public Maquina() {
    }

    public Maquina(Long id, String nombre, String modelo, String numeroSerie,
                   LocalDate fechaCompra, String estado, TipoMaquina tipoMaquina) {
        this.id = id;
        this.nombre = nombre;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.fechaCompra = fechaCompra;
        this.estado = estado;
        this.tipoMaquina = tipoMaquina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public TipoMaquina getTipoMaquina() {
        return tipoMaquina;
    }

    public void setTipoMaquina(TipoMaquina tipoMaquina) {
        this.tipoMaquina = tipoMaquina;
    }
}
