package com.tunisia.financial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main Application Class for Secure Financial Intelligence System
 * 
 * @author Jiji
 * @version 1.0.0
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableJpaAuditing
public class FinancialApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancialApplication.class, args);
        System.out.println("==============================================");
        System.out.println("Financial Security System Started Successfully");
        System.out.println("Server running on: http://localhost:8080");
        System.out.println("H2 Console: http://localhost:8080/h2-console");
        System.out.println("==============================================");
    }
}
