package com.example.orchestration;

import com.example.commons.configuration.CommonControllerAdvice;
import com.example.commons.configuration.KafkaProducerConfig;
import com.example.commons.configuration.RestConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {CommonControllerAdvice.class, KafkaProducerConfig.class, RestConfiguration.class})
public class OrchestrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrchestrationApplication.class, args);
    }

}
