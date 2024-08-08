package com.example.receivable;

import com.example.commons.configuration.CommonControllerAdvice;
import com.example.commons.configuration.KafkaProducerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {CommonControllerAdvice.class, KafkaProducerConfig.class})
public class ReceivableApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReceivableApplication.class, args);
    }

}
