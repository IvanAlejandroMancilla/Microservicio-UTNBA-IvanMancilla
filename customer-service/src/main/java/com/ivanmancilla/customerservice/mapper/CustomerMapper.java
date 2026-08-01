package com.ivanmancilla.customerservice.mapper;

import com.ivanmancilla.customerservice.dto.CustomerRequestDTO;
import com.ivanmancilla.customerservice.dto.CustomerResponseDTO;
import com.ivanmancilla.customerservice.dto.ProductDTO;
import com.ivanmancilla.customerservice.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper implements GenericMapper<Customer, CustomerRequestDTO, CustomerResponseDTO> {

    @Override
    public Customer toEntity(CustomerRequestDTO dto) {
        if (dto == null) return null;
        return new Customer(
                null,
                dto.getNombre(),
                dto.getDocumento(),
                dto.getEmail(),
                dto.getSaldo()
        );
    }

    @Override
    public CustomerResponseDTO toResponseDTO(Customer customer) {
        if (customer == null) return null;
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getNombre(),
                customer.getDocumento(),
                customer.getEmail(),
                customer.getSaldo(),
                new ArrayList<>()
        );
    }

    @Override
    public List<CustomerResponseDTO> toResponseDTOList(List<Customer> entityList) {
        if (entityList == null) return new ArrayList<>();
        return entityList.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public CustomerResponseDTO toResponseDTOWithProducts(Customer customer, List<ProductDTO> productos) {
        CustomerResponseDTO dto = toResponseDTO(customer);
        if (dto != null) {
            dto.setProductos(productos != null ? productos : new ArrayList<>());
        }
        return dto;
    }
}
