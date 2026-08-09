package com.ivanmancilla.productservice.config;

import com.ivanmancilla.productservice.entity.Product;
import com.ivanmancilla.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class ProductDataInitializer {

    @Bean
    public CommandLineRunner initProductData(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                productRepository.save(new Product(
                        null, 1L, "CAJA_AHORRO", "Caja de Ahorro en Pesos", "Cuenta a la vista en pesos argentinos",
                        new BigDecimal("150000.00"), null, true,
                        LocalDate.of(2024, 1, 10), null
                ));

                productRepository.save(new Product(
                        null, 1L, "TARJETA_CREDITO", "Tarjeta de Crédito VISA Gold", "Tarjeta de crédito internacional",
                        new BigDecimal("1000000.00"), null, true,
                        LocalDate.of(2024, 2, 15), LocalDate.of(2028, 2, 15)
                ));

                productRepository.save(new Product(
                        null, 1L, "PLAZO_FIJO", "Plazo Fijo Uva", "Plazo fijo ajustado por CER",
                        new BigDecimal("500000.00"), new BigDecimal("110.00"), true,
                        LocalDate.of(2026, 6, 1), LocalDate.of(2026, 12, 1)
                ));

                productRepository.save(new Product(
                        null, 2L, "CAJA_AHORRO", "Caja de Ahorro en Dólares", "Cuenta a la vista en dólares",
                        new BigDecimal("2500.00"), null, true,
                        LocalDate.of(2025, 3, 5), null
                ));
            }
        };
    }
}
