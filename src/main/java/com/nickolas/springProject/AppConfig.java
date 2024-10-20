package com.nickolas.springProject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = "com.nickolas.springProject")
public class AppConfig {
    @Bean
    @Scope("prototype")
    public Cart cart() {
        return new Cart(productRepository());
    }

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepository();
    }
}
