package com.nickolas.springProject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CartServiceRunner {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CartService cartService = context.getBean(CartService.class);


        Cart cart = cartService.getNewCart();


        cartService.addProduct(cart, 1L, 2);
        cartService.addProduct(cart, 2L, 1);
        cartService.addProduct(cart, 3L, 2);
        cartService.addProduct(cart, 4L, 1);
        cartService.addProduct(cart, 5L, 1);


        String title = "CART";
        int consoleWidth = 150;
        int padding = (consoleWidth - title.length()) / 2;
        System.out.println(" ".repeat(padding) + title.toUpperCase());

        cartService.printCart(cart);


        System.out.println("Total Sum: " + cartService.getSum(cart));
    }

}

