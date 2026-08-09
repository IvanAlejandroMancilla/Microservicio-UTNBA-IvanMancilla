package com.ivanmancilla.productservice.mapper;

import com.ivanmancilla.productservice.dto.ProductRequestDTO;
import com.ivanmancilla.productservice.dto.ProductResponseDTO;
import com.ivanmancilla.productservice.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper implements GenericMapper<Product, ProductRequestDTO, ProductResponseDTO> {

    @Override
    public Product toEntity(ProductRequestDTO dto) {
        if (dto == null) return null;
        return new Product(
                null,
                dto.getClienteId(),
                dto.getTipo(),
                dto.getNombre(),
                dto.getDescripcion(),
                dto.getMontoAsociado(),
                dto.getTasaInteres(),
                dto.isActivo(),
                dto.getFechaInicio(),
                dto.getFechaVencimiento()
        );
    }

    @Override
    public ProductResponseDTO toResponseDTO(Product product) {
        if (product == null) return null;
        return new ProductResponseDTO(
                product.getId(),
                product.getClienteId(),
                product.getTipo(),
                product.getNombre(),
                product.getDescripcion(),
                product.getMontoAsociado(),
                product.getTasaInteres(),
                product.isActivo(),
                product.getFechaInicio(),
                product.getFechaVencimiento()
        );
    }

    @Override
    public List<ProductResponseDTO> toResponseDTOList(List<Product> entityList) {
        if (entityList == null) return new ArrayList<>();
        return entityList.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
