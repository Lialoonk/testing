package com.example.order.stub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/stub/api/v1/products")
public class CatalogStubController {

    private static final Logger log = LoggerFactory.getLogger(CatalogStubController.class);

    private static final String SLOW_ID = "095";

    private final Map<String, Map<String, Object>> products = Map.of(
            "011", Map.of("id", "011", "name", "Кавомашина DeLonghi Magnifica", "price", new BigDecimal("15499.00"), "warehouse", "LV-2"),
            "042", Map.of("id", "042", "name", "Навушники Sony WH-1000XM5", "price", new BigDecimal("12999.00"), "warehouse", "KY-1"),
            SLOW_ID, Map.of("id", SLOW_ID, "name", "Пилосос Dyson V15", "price", new BigDecimal("27999.00"), "warehouse", "OD-3")
    );

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProduct(@PathVariable("id") String id,
                                                          @RequestHeader(value = "X-Correlation-Id", required = false) String correlationId) {
        log.info("stub got request id={}, correlationId={}", id, correlationId);

        if (SLOW_ID.equals(id)) {
            log.warn("stub sleeps 5000 ms for id={}", id);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        Map<String, Object> product = products.get(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }
}
