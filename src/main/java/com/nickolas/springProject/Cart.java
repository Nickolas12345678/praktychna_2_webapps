package com.nickolas.springProject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class Cart {
    private Map<Product, Integer> cartMap = new HashMap<>();
    private final ProductRepository productRepository;


    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Map<Product, Integer> getCartMap() {
        return cartMap;
    }

    public void addProduct(Product product, Integer quantity) {
        cartMap.merge(product, quantity, Integer::sum);
    }

    public void delProduct(Product product, Integer quantity) {
        cartMap.computeIfPresent(product, (key, val) -> val - quantity <= 0 ? null : val - quantity);
    }

    public BigDecimal getSum() {
        return cartMap.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getProductQuantity(Product product) {
        return cartMap.getOrDefault(product, 0);
    }
}
