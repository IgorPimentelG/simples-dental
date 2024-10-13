package com.simplesdental.api.infrastructure.configs;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.*;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
          .info(new Info()
            .title("Simples Dental API")
            .version("0.0.1")
            .contact(new Contact()
              .name("Igor Pimentel")
              .email("dev.igorpimentel@gmail.com")
              .url("https://www.linkedin.com/in/igor-pimentel-g/")
            )
          ).addTagsItem(new Tag().name("Contatos").description("Operações para gerenciar contatos")
          ).addTagsItem(new Tag().name("Profissionais").description("Operações para gerenciar profissionais")
        );
    }
}