package io.challenge.bestmatched.restaurants.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerCustomConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Best Matched Restaurant")
                        .contact(new Contact()
                                .name("Ralf Drehmer Wink")
                                .url("http://github.com/ralfdw3"))
                        .version("1.0.0"));
    }
}
