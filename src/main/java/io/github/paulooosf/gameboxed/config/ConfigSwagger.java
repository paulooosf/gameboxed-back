package io.github.paulooosf.gameboxed.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigSwagger {

    @Bean
    OpenAPI myOpenApi() {
        Contact contact = new Contact()
                .email("paulooosf@gmail.com")
                .name("Paulo Henrique")
                .url("https://github.com/paulooosf");

        License apacheLicense = new License()
                .name("Apache License")
                .url("https://www.apache.org/license/LICENSE-2.0");

        Info info = new Info()
                .title("API GameboXed")
                .version("1.0")
                .contact(contact)
                .description("API desenvolvida para gerenciar os dados do projeto GameboXed, "
                        + "\num portal de avaliação de jogos.")
                .license(apacheLicense);

        return new OpenAPI()
                .info(info);
    }
}
