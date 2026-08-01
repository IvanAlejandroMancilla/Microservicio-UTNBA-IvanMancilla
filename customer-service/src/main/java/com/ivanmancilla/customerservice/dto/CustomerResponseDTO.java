package com.ivanmancilla.customerservice.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CustomerResponseDTO {

    private Long id;
    private String nombre;
    private String documento;
    private String email;
    private BigDecimal saldo;
    private List<ProductDTO> productos = new ArrayList<>();

    public CustomerResponseDTO() {
    }

    public CustomerResponseDTO(Long id, String nombre, String documento, String email, BigDecimal saldo, List<ProductDTO> productos) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.email = email;
        this.saldo = saldo;
        this.productos = productos != null ? productos : new ArrayList<>();
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<ProductDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductDTO> productos) {
        this.productos = productos;
    }
}
