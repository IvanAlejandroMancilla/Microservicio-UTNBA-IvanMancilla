package com.ivanmancilla.productservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductResponseDTO {

    private Long id;
    private Long clienteId;
    private String tipo;
    private String nombre;
    private String descripcion;
    private BigDecimal montoAsociado;
    private BigDecimal tasaInteres;
    private boolean activo;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Long id, Long clienteId, String tipo, String nombre, String descripcion, BigDecimal montoAsociado,
                               BigDecimal tasaInteres, boolean activo, LocalDate fechaInicio, LocalDate fechaVencimiento) {
        this.id = id;
        this.clienteId = clienteId;
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.montoAsociado = montoAsociado;
        this.tasaInteres = tasaInteres;
        this.activo = activo;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMontoAsociado() {
        return montoAsociado;
    }

    public void setMontoAsociado(BigDecimal montoAsociado) {
        this.montoAsociado = montoAsociado;
    }

    public BigDecimal getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(BigDecimal tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
