package com.example.transactions;

import com.example.commons.configuration.CommonControllerAdvice;
import com.example.commons.configuration.CorrelationFilterConfiguration;
import com.example.commons.configuration.KafkaProducerConfig;
import com.example.commons.configuration.RestConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {CommonControllerAdvice.class, KafkaProducerConfig.class, RestConfiguration.class, CorrelationFilterConfiguration.class})
public class TransactionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionsApplication.class, args);
    }

}
