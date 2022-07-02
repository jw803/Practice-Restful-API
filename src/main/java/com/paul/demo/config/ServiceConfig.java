package com.paul.demo.config;

import com.paul.demo.repository.ProductRepository;
import com.paul.demo.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public ProductService productService(ProductRepository repository) {
        return new ProductService(repository);
    }
}
