package com.example.company;

import com.example.commons.configuration.CommonControllerAdvice;
import com.example.commons.configuration.KafkaProducerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {CommonControllerAdvice.class, KafkaProducerConfig.class})
public class CompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyApplication.class, args);
    }

}
