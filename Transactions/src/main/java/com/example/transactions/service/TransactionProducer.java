package com.example.transactions.service;

import com.example.commons.model.AccountDto;
import com.example.commons.model.TransactionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static com.example.commons.constant.CommonConstant.TRANSACTIONS_TOPIC;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionProducer {

    private final KafkaTemplate<String, Map<String, Object>> kafkaTemplate;

    @Transactional
    public void sendMessage(TransactionDto message, String correlationId) {
        Message<TransactionDto> kakfkaMessage = MessageBuilder.withPayload(message)
                .setHeader(KafkaHeaders.TOPIC, TRANSACTIONS_TOPIC)
                .setHeader(KafkaHeaders.CORRELATION_ID, correlationId)
                .build();
        kafkaTemplate.send(kakfkaMessage);
    }
}
