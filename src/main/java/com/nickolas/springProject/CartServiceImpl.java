package com.nickolas.springProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

@Service
public class CartServiceImpl implements CartService {
    private final ProductRepository productRepository;
    private final ApplicationContext context;

    @Autowired
    public CartServiceImpl(ProductRepository productRepository, ApplicationContext context) {
        this.productRepository = productRepository;
        this.context = context;
    }

    @Override
    public Cart getNewCart() {
        return context.getBean(Cart.class);
    }

    @Override
    public void addProduct(Cart cart, Product product, Integer quantity) {
        cart.addProduct(product, quantity);
    }

    @Override
    public void addProduct(Cart cart, Long prodId, Integer quantity) {
        Product product = productRepository.findById(prodId);
        if (product != null) {
            cart.addProduct(product, quantity);
        }
    }

    @Override
    public void delProduct(Cart cart, Product product, Integer quantity) {
        cart.delProduct(product, quantity);
    }

    @Override
    public BigDecimal getSum(Cart cart) {
        return cart.getSum();
    }

    @Override
    public void printCart(Cart cart) {
        cart.getCartMap().forEach((product, quantity) ->
                System.out.println("Product: " + product.getName() + ", Quantity: " + quantity));
    }

    @Override
    public int getProductQuantity(Cart cart, Product product) {
        return cart.getProductQuantity(product);
    }

    @Override
    public int getProductQuantity(Cart cart, Long prodId) {
        Product product = productRepository.findById(prodId);
        return product != null ? cart.getProductQuantity(product) : 0;
    }
}
