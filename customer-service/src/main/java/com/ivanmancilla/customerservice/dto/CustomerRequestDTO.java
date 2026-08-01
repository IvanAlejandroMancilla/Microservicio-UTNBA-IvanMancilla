package com.ivanmancilla.customerservice.dto;

import java.math.BigDecimal;

public class CustomerRequestDTO {

    private String nombre;
    private String documento;
    private String email;
    private BigDecimal saldo;

    public CustomerRequestDTO() {
    }

    public CustomerRequestDTO(String nombre, String documento, String email, BigDecimal saldo) {
        this.nombre = nombre;
        this.documento = documento;
        this.email = email;
        this.saldo = saldo;
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
}
