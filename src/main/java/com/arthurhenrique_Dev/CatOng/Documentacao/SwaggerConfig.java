package com.arthurhenrique_Dev.CatOng.Documentacao;

import io.swagger.v3.oas.models.info.Info; // <— correct model class
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("CatOng")
                .version("1.0.0")
                .description("Documentação da API da CatOng")
        );
    }
}
