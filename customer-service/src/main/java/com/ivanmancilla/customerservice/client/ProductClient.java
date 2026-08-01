package com.ivanmancilla.customerservice.client;

import com.ivanmancilla.customerservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/productos/cliente/{clienteId}")
    List<ProductDTO> getProductosPorCliente(@PathVariable("clienteId") Long clienteId);
}
