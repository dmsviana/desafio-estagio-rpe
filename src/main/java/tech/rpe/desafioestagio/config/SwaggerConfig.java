package tech.rpe.desafioestagio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI configOpenApi() {
        return new OpenAPI().info(
                new Info().description("API RESTful for managing people and employees - Challenge Retail Payment Ecosystem")
                        .title("Desafio Evoluir - RPE")
                        .version("1.0")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact().email("marcello.razer@gmail.com"))
        );
    }


}