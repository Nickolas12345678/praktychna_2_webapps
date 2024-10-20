package com.nickolas.springProject;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class ProductRepository {
    private Map<Long, Product> productMap = new HashMap<>();

    @PostConstruct
    public void init() {
        productMap.put(1L, new Product(1L, "Bread", BigDecimal.valueOf(100.0)));
        productMap.put(2L, new Product(2L, "Milk", BigDecimal.valueOf(150.0)));
        productMap.put(3L, new Product(3L, "Cheese", BigDecimal.valueOf(200.0)));
        productMap.put(4L, new Product(4L, "Chicken", BigDecimal.valueOf(250.0)));
        productMap.put(5L, new Product(5L, "Apple", BigDecimal.valueOf(300.0)));
    }

    public List<Product> findAll() {
        return productMap.values().stream().collect(Collectors.toList());
    }

    public void saveOrUpdate(Product product) {
        productMap.put(product.getId(), product);
    }

    public Product findById(Long id) {
        return productMap.get(id);
    }

    public void deleteById(Long id) {
        productMap.remove(id);
    }
}
