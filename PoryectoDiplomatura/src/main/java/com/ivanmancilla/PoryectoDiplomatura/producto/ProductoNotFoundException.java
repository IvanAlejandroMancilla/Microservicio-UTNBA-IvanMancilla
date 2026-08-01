package com.ivanmancilla.PoryectoDiplomatura.producto;

public class ProductoNotFoundException extends RuntimeException {
    public ProductoNotFoundException(String message) {
        super(message);
    }
}
