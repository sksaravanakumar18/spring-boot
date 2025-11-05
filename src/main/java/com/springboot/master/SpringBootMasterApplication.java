package com.springboot.master;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main Spring Boot Application Class
 * 
 * This is the entry point of our Spring Boot application.
 * Key annotations explained:
 * 
 * @SpringBootApplication - This is a convenience annotation that combines:
 *   - @Configuration: Tags the class as a source of bean definitions
 *   - @EnableAutoConfiguration: Enables Spring Boot's auto-configuration
 *   - @ComponentScan: Enables component scanning on the package
 * 
 * @EnableCaching - Enables Spring's annotation-driven cache management
 * @EnableAsync - Enables Spring's asynchronous method execution capability
 * @EnableScheduling - Enables Spring's scheduled task execution capability
 */
@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableScheduling
public class SpringBootMasterApplication {

    /**
     * Main method - Entry point of the application
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMasterApplication.class, args);
    }
}
