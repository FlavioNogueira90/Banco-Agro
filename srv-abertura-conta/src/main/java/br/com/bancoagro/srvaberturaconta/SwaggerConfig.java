package br.com.bancoagro.srvaberturaconta;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi swaggerUiConfigParameters(){
        return GroupedOpenApi.builder()
                .group("user")
                .pathsToExclude("/api/v2/**")
                .pathsToMatch("/api/v1/**")
                .build();
    }
}