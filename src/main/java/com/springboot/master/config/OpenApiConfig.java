package com.springboot.master.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI/Swagger Configuration
 * 
 * Demonstrates:
 * - API documentation configuration
 * - OpenAPI customization
 * - Bean definition
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot Master Project API")
                        .version("1.0.0")
                        .description("A comprehensive Spring Boot learning project demonstrating all core concepts and best practices")
                        .contact(new Contact()
                                .name("Spring Boot Master")
                                .email("support@springbootmaster.com")
                                .url("https://github.com/springboot-master"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
