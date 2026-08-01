package com.ivanmancilla.PoryectoDiplomatura.config;

import com.ivanmancilla.PoryectoDiplomatura.producto.Producto;
import com.ivanmancilla.PoryectoDiplomatura.producto.ProductoRepository;
import com.ivanmancilla.PoryectoDiplomatura.producto.TipoProducto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(ProductoRepository productoRepository) {
        return args -> {
            Producto producto1 = new Producto();
            producto1.setClienteId(1L);
            producto1.setNombre("Caja de Ahorro en Pesos");
            producto1.setDescripcion("Cuenta de ahorro principal");
            producto1.setTipo(TipoProducto.CAJA_AHORRO);
            producto1.setMontoAsociado(new BigDecimal("150000.00"));
            producto1.setTasaInteres(new BigDecimal("0.00"));
            producto1.setActivo(true);
            producto1.setFechaInicio(LocalDate.now().minusMonths(6));
            producto1.setFechaAlta(LocalDateTime.now());
            productoRepository.save(producto1);

            Producto producto2 = new Producto();
            producto2.setClienteId(1L);
            producto2.setNombre("Tarjeta de Crédito VISA Gold");
            producto2.setDescripcion("Límite $1.000.000 - Vencimiento 2029");
            producto2.setTipo(TipoProducto.TARJETA_CREDITO);
            producto2.setMontoAsociado(new BigDecimal("1000000.00"));
            producto2.setTasaInteres(new BigDecimal("45.00"));
            producto2.setActivo(true);
            producto2.setFechaInicio(LocalDate.now().minusYears(1));
            producto2.setFechaVencimiento(LocalDate.now().plusYears(3));
            producto2.setFechaAlta(LocalDateTime.now());
            productoRepository.save(producto2);

            Producto producto3 = new Producto();
            producto3.setClienteId(1L);
            producto3.setNombre("Plazo Fijo Uva");
            producto3.setDescripcion("Inversión a 90 días");
            producto3.setTipo(TipoProducto.PLAZO_FIJO);
            producto3.setMontoAsociado(new BigDecimal("500000.00"));
            producto3.setTasaInteres(new BigDecimal("70.50"));
            producto3.setActivo(true);
            producto3.setFechaInicio(LocalDate.now().minusDays(30));
            producto3.setFechaVencimiento(LocalDate.now().plusDays(60));
            producto3.setFechaAlta(LocalDateTime.now());
            productoRepository.save(producto3);
        };
    }
}
