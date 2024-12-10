package com.example.e_commerce.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("JWT Cookie"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("JWT Cookie", new SecurityScheme()
                                .name("jwt") // Cookie name
                                .type(Type.APIKEY)
                                .in(In.COOKIE)
                                .description("JWT Token stored in cookie for authentication")));
    }
}

