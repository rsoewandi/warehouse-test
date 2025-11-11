package com.warehouse.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI warehouseOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Warehouse Management API")
                        .version("1.0.0")
                        .description("""
                        RESTful API for managing shop warehouse inventory,
                        including Items, Variants, Pricing, and Stock.
                        """)
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
