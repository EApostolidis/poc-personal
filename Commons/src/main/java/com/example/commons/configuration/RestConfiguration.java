package com.example.commons.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

    @Bean
    public RestTemplateComponent restTemplateComponent() {
        return new RestTemplateComponent(new RestTemplate());
    }
}
