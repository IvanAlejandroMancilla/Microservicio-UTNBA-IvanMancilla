package com.ivanmancilla.productservice.repository;

import com.ivanmancilla.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByClienteId(Long clienteId);

    List<Product> findByActivoTrue();
}
