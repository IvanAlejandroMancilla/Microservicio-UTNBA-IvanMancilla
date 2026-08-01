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
            producto1.setNombreProducto("Caja de Ahorro en Pesos");
            producto1.setDetalleProducto("Cuenta de ahorro principal");
            producto1.setTipo(TipoProducto.CAJA_AHORRO);
            producto1.setMontoAsociadoProducto(new BigDecimal("150000.00"));
            producto1.setTasaInteresProducto(new BigDecimal("0.00"));
            producto1.setActivoProducto(true);
            producto1.setFechaInicioProducto(LocalDate.now().minusMonths(6));
            producto1.setFechaAltaProducto(LocalDateTime.now());
            productoRepository.save(producto1);

            Producto producto2 = new Producto();
            producto2.setClienteId(1L);
            producto2.setNombreProducto("Tarjeta de Crédito VISA Gold");
            producto2.setDetalleProducto("Límite $1.000.000 - Vencimiento 2029");
            producto2.setTipo(TipoProducto.TARJETA_CREDITO);
            producto2.setMontoAsociadoProducto(new BigDecimal("1000000.00"));
            producto2.setTasaInteresProducto(new BigDecimal("45.00"));
            producto2.setActivoProducto(true);
            producto2.setFechaInicioProducto(LocalDate.now().minusYears(1));
            producto2.setFechaVencimientoProducto(LocalDate.now().plusYears(3));
            producto2.setFechaAltaProducto(LocalDateTime.now());
            productoRepository.save(producto2);

            Producto producto3 = new Producto();
            producto3.setClienteId(1L);
            producto3.setNombreProducto("Plazo Fijo Uva");
            producto3.setDetalleProducto("Inversión a 90 días");
            producto3.setTipo(TipoProducto.PLAZO_FIJO);
            producto3.setMontoAsociadoProducto(new BigDecimal("500000.00"));
            producto3.setTasaInteresProducto(new BigDecimal("70.50"));
            producto3.setActivoProducto(true);
            producto3.setFechaInicioProducto(LocalDate.now().minusDays(30));
            producto3.setFechaVencimientoProducto(LocalDate.now().plusDays(60));
            producto3.setFechaAltaProducto(LocalDateTime.now());
            productoRepository.save(producto3);
        };
    }
}
