package ru.tasktrade.crudservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration(value = "myConfig")
public class AppConfig {

    @Bean
    public WebClient webUserClient() {
        return WebClient.builder().baseUrl("http://localhost:8080/users/").build();
    }

}
