package com.layon.serviceflow.infra;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ServiceFlow API")
                        .version("v1.0")
                        .description("API REST para gerenciamento de fluxos de serviços técnicos, desenvolvida com foco em boas práticas, exclusão lógica e performance.")
                        .contact(new Contact()
                                .name("Layon Carvalho")
                                .email("layon.300@hotmail.com")));
    }
}