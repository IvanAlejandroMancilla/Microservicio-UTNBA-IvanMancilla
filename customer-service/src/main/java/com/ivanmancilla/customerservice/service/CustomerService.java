package com.ivanmancilla.customerservice.service;

import com.ivanmancilla.customerservice.client.ProductClient;
import com.ivanmancilla.customerservice.dto.CustomerRequestDTO;
import com.ivanmancilla.customerservice.dto.CustomerResponseDTO;
import com.ivanmancilla.customerservice.dto.ProductDTO;
import com.ivanmancilla.customerservice.entity.Customer;
import com.ivanmancilla.customerservice.exception.CustomerNotFoundException;
import com.ivanmancilla.customerservice.exception.DuplicateCustomerException;
import com.ivanmancilla.customerservice.mapper.CustomerMapper;
import com.ivanmancilla.customerservice.repository.CustomerRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final ProductClient productClient;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper, ProductClient productClient) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.productClient = productClient;
    }

    public List<CustomerResponseDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.toResponseDTOList(customers);
    }

    public CustomerResponseDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente no encontrado con ID: " + id));
        return customerMapper.toResponseDTO(customer);
    }

    public CustomerResponseDTO getCustomerWithProducts(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente no encontrado con ID: " + id));

        List<ProductDTO> productos;
        try {
            productos = productClient.getProductosPorCliente(id);
        } catch (FeignException e) {
            logger.warn("No se pudieron obtener los productos del cliente {} desde product-service: {}", id, e.getMessage());
            productos = new ArrayList<>();
        }

        return customerMapper.toResponseDTOWithProducts(customer, productos);
    }

    public CustomerResponseDTO createCustomer(CustomerRequestDTO requestDTO) {
        customerRepository.findByDocumento(requestDTO.getDocumento())
                .ifPresent(existing -> {
                    throw new DuplicateCustomerException("Ya existe un cliente con el documento: " + requestDTO.getDocumento());
                });

        Customer customer = customerMapper.toEntity(requestDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toResponseDTO(savedCustomer);
    }

    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO requestDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente no encontrado con ID: " + id));

        existingCustomer.setNombre(requestDTO.getNombre());
        existingCustomer.setDocumento(requestDTO.getDocumento());
        existingCustomer.setEmail(requestDTO.getEmail());
        existingCustomer.setSaldo(requestDTO.getSaldo());

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return customerMapper.toResponseDTO(updatedCustomer);
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Cliente no encontrado con ID: " + id);
        }
        customerRepository.deleteById(id);
    }
}
