package com.ivanmancilla.customerservice.service;

import com.ivanmancilla.customerservice.client.ProductClient;
import com.ivanmancilla.customerservice.dto.CustomerRequestDTO;
import com.ivanmancilla.customerservice.dto.CustomerResponseDTO;
import com.ivanmancilla.customerservice.dto.ProductDTO;
import com.ivanmancilla.customerservice.entity.Customer;
import com.ivanmancilla.customerservice.exception.CustomerNotFoundException;
import com.ivanmancilla.customerservice.mapper.CustomerMapper;
import com.ivanmancilla.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired(required = false)
    private ProductClient productClient;

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
        
        List<ProductDTO> productos = new ArrayList<>();
        if (productClient != null) {
            try {
                productos = productClient.getProductosPorCliente(id);
            } catch (Exception e) {
                productos = new ArrayList<>();
            }
        }

        return customerMapper.toResponseDTOWithProducts(customer, productos);
    }

    public CustomerResponseDTO createCustomer(CustomerRequestDTO requestDTO) {
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
