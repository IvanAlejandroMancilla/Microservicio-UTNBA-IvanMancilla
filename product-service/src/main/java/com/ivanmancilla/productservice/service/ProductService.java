package com.ivanmancilla.productservice.service;

import com.ivanmancilla.productservice.dto.ProductRequestDTO;
import com.ivanmancilla.productservice.dto.ProductResponseDTO;
import com.ivanmancilla.productservice.entity.Product;
import com.ivanmancilla.productservice.exception.ProductNotFoundException;
import com.ivanmancilla.productservice.mapper.ProductMapper;
import com.ivanmancilla.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductResponseDTO> getAllProducts() {
        return productMapper.toResponseDTOList(productRepository.findAll());
    }

    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado con ID: " + id));
        return productMapper.toResponseDTO(product);
    }

    public List<ProductResponseDTO> getProductsByClienteId(Long clienteId) {
        return productMapper.toResponseDTOList(productRepository.findByClienteId(clienteId));
    }

    public List<ProductResponseDTO> getActiveProducts() {
        return productMapper.toResponseDTOList(productRepository.findByActivoTrue());
    }

    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        Product product = productMapper.toEntity(requestDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponseDTO(savedProduct);
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO requestDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado con ID: " + id));

        existingProduct.setClienteId(requestDTO.getClienteId());
        existingProduct.setTipo(requestDTO.getTipo());
        existingProduct.setNombre(requestDTO.getNombre());
        existingProduct.setDescripcion(requestDTO.getDescripcion());
        existingProduct.setMontoAsociado(requestDTO.getMontoAsociado());
        existingProduct.setTasaInteres(requestDTO.getTasaInteres());
        existingProduct.setActivo(requestDTO.isActivo());
        existingProduct.setFechaInicio(requestDTO.getFechaInicio());
        existingProduct.setFechaVencimiento(requestDTO.getFechaVencimiento());

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toResponseDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Producto no encontrado con ID: " + id);
        }
        productRepository.deleteById(id);
    }
}
