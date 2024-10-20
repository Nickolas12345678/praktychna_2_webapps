package com.nickolas;

import com.nickolas.springProject.Cart;
import com.nickolas.springProject.CartServiceImpl;
import com.nickolas.springProject.Product;
import com.nickolas.springProject.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class CartServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    private Cart cart;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cart = cartService.getNewCart();


        when(productRepository.findById(1L)).thenReturn(new Product(1L, "Bread", BigDecimal.valueOf(100.0)));
        when(productRepository.findById(2L)).thenReturn(new Product(2L, "Milk", BigDecimal.valueOf(150.0)));
        when(productRepository.findById(3L)).thenReturn(new Product(3L, "Cheese", BigDecimal.valueOf(150.0)));
        when(productRepository.findById(4L)).thenReturn(new Product(4L, "Chicken", BigDecimal.valueOf(150.0)));
        when(productRepository.findById(5L)).thenReturn(new Product(2L, "Apple", BigDecimal.valueOf(150.0)));
    }

    @Test
    void testAddProductToCart() {
        cartService.addProduct(cart, 1L, 2);
        assertEquals(2, cart.getProductQuantity(productRepository.findById(1L)));
        assertEquals(BigDecimal.valueOf(200.0), cart.getSum());
    }

    @Test
    void testAddMultipleProducts() {
        cartService.addProduct(cart, 1L, 2);
        cartService.addProduct(cart, 2L, 1);

        assertEquals(2, cart.getProductQuantity(productRepository.findById(1L)));
        assertEquals(1, cart.getProductQuantity(productRepository.findById(2L)));
        assertEquals(BigDecimal.valueOf(350.0), cart.getSum());
    }

    @Test
    void testRemoveProductFromCart() {
        cartService.addProduct(cart, 1L, 2);
        cartService.delProduct(cart, productRepository.findById(1L), 1);

        assertEquals(1, cart.getProductQuantity(productRepository.findById(1L)));
        assertEquals(BigDecimal.valueOf(100.0), cart.getSum());
    }

    @Test
    void testGetTotalSum() {
        cartService.addProduct(cart, 1L, 1);
        cartService.addProduct(cart, 2L, 1);

        assertEquals(BigDecimal.valueOf(250.0), cart.getSum());
    }

    @Test
    void testPrintCart() {
        cartService.addProduct(cart, 1L, 2);
        cartService.addProduct(cart, 2L, 1);


        assertEquals(2, cart.getCartMap().size());
    }
}
