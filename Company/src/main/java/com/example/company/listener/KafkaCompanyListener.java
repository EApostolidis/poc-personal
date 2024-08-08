package com.example.company.listener;

import com.example.commons.model.CompanyDto;
import com.example.company.service.CompanyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class KafkaCompanyListener {

    private final CompanyService companyService;

    @KafkaListener(topics = "create-company", groupId = "company-consumer-group")
    public void listen(ConsumerRecord<String, String> record) throws JsonProcessingException {
        System.out.println("Received message headers: " + record.headers());
        System.out.println("Received message key: " + record.key());
        System.out.println("Received message body: " + record.value());
        ObjectMapper objectMapper = new ObjectMapper();
        CompanyDto request = objectMapper.readValue(record.value(), CompanyDto.class);
        companyService.createCompany(request);
    }

}
