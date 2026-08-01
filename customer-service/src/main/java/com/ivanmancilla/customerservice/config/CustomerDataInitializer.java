package com.ivanmancilla.customerservice.config;

import com.ivanmancilla.customerservice.entity.Customer;
import com.ivanmancilla.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class CustomerDataInitializer {

    @Bean
    public CommandLineRunner initCustomerData(CustomerRepository customerRepository) {
        return args -> {
            if (customerRepository.count() == 0) {
                Customer customer1 = new Customer(
                        null,
                        "Ivan Mancilla",
                        "12345678",
                        "ivan@example.com",
                        new BigDecimal("500000.00")
                );
                customerRepository.save(customer1);

                Customer customer2 = new Customer(
                        null,
                        "Cliente de Prueba",
                        "87654321",
                        "prueba@example.com",
                        new BigDecimal("250000.00")
                );
                customerRepository.save(customer2);
            }
        };
    }
}
