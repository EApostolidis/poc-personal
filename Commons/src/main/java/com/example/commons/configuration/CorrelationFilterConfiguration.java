package com.example.commons.configuration;

import com.example.commons.utility.CorrelationIdFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CorrelationFilterConfiguration {

    @Bean
    public CorrelationIdFilter correlationFilterConfiguration() {
        return new CorrelationIdFilter();
    }
}
