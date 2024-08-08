package com.example.account.listener;

import com.example.account.service.AccountService;
import com.example.commons.model.TransactionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.example.commons.constant.CommonConstant.TRANSACTIONS_TOPIC;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaAccountListener {

    private final AccountService accountService;

    @KafkaListener(topics = TRANSACTIONS_TOPIC, groupId = "account-consumer-group")
    public void listen(ConsumerRecord<String, String> record) throws JsonProcessingException {
        log.info("Received message headers: {}", record.headers());
        log.info("Received message key: {}", record.key());
        log.info("Received message body: {}", record.value());
        ObjectMapper objectMapper = new ObjectMapper();
        TransactionDto request = objectMapper.readValue(record.value(), TransactionDto.class);
        accountService.calculateAccountBalance(request);
    }
}
