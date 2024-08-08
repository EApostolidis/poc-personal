package com.example.orchestration.service;

import com.example.commons.model.CompanyDto;
import com.example.commons.model.ReceivableDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyProducerService {

    private final KafkaTemplate<String, Map<String, Object>> kafkaTemplate;
    @Transactional
    public void sendMessage(Message<CompanyDto> message) {
        Message<CompanyDto> kakfkaMessage = MessageBuilder.withPayload(message.getPayload())
                .setHeader(KafkaHeaders.TOPIC, "create-company")
                .build();
        kafkaTemplate.send(kakfkaMessage);
    }
}
