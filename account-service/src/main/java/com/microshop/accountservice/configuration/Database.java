package com.microshop.accountservice.configuration;

import com.microshop.accountservice.entity.Customer;
import com.microshop.accountservice.repositories.ICustomerRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {

    @Bean
    InitializingBean seedDatabase(final ICustomerRepository customerRepository) {
        return () -> {
            customerRepository.save(new Customer("Zorkov"));
            customerRepository.save(new Customer("Lorne"));
            customerRepository.save(new Customer("Matthias"));
        };
    }
}
