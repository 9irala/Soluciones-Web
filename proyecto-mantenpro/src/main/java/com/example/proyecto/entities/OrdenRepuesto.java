package com.example.proyecto.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "orden_repuestos")
public class OrdenRepuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer cantidad;

    @NotNull(message = "La orden de mantenimiento es obligatoria")
    @ManyToOne
    @JoinColumn(name = "orden_id", nullable = false)
    private OrdenMantenimiento orden;

    @NotNull(message = "El repuesto es obligatorio")
    @ManyToOne
    @JoinColumn(name = "repuesto_id", nullable = false)
    private Repuesto repuesto;

    public OrdenRepuesto() {
    }

    public OrdenRepuesto(Long id, Integer cantidad, OrdenMantenimiento orden, Repuesto repuesto) {
        this.id = id;
        this.cantidad = cantidad;
        this.orden = orden;
        this.repuesto = repuesto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public OrdenMantenimiento getOrden() {
        return orden;
    }

    public void setOrden(OrdenMantenimiento orden) {
        this.orden = orden;
    }

    public Repuesto getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }
}
