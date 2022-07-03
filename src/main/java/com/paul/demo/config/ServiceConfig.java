package com.paul.demo.config;

import com.paul.demo.auth.UserIdentity;
import com.paul.demo.repository.AppUserRepository;
import com.paul.demo.repository.ProductRepository;
import com.paul.demo.service.AppUserService;
import com.paul.demo.service.MailService;
import com.paul.demo.service.ProductService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ServiceConfig {
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ProductService productService(ProductRepository repository, MailService mailService,
                                         UserIdentity userIdentity) {
        System.out.println("Product Service is created.");
        return new ProductService(repository, mailService, userIdentity);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AppUserService appUserService(AppUserRepository repository) {
        return new AppUserService(repository);
    }
}
