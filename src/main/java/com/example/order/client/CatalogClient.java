package com.example.order.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/api/v1/products")
public interface CatalogClient {

    @GetExchange("/{id}")
    ProductDto getProductById(@PathVariable("id") String id);
}
