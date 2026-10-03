package com.example.order.web;

import com.example.order.client.CatalogClient;
import com.example.order.client.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final CatalogClient catalogClient;

    public OrderController(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    @GetMapping("/products/{id}")
    public Map<String, Object> checkProduct(@PathVariable("id") String id,
                                            @RequestParam(value = "qty", defaultValue = "1") int qty) {
        ProductDto product = catalogClient.getProductById(id);
        log.info("catalog returned {}", product);

        BigDecimal total = product.price().multiply(BigDecimal.valueOf(qty));
        return Map.of("product", product, "qty", qty, "total", total);
    }
}
