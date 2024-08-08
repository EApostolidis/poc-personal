package com.example.receivable.listener;

import com.example.commons.model.FinanceReceivableDto;
import com.example.commons.model.ReceivableDto;
import com.example.receivable.service.ReceivableService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.example.commons.utility.KafkaUtility.readHeader;

@Component
@AllArgsConstructor
@Slf4j
public class KafkaReceivableListener {

    private final ReceivableService receivableService;

    @KafkaListener(topics = "create-receivable", groupId = "receivable-consumer-group")
    public void create(ConsumerRecord<String, String> record) throws JsonProcessingException {
        log.info(readHeader(record, "status"));
        ObjectMapper objectMapper = new ObjectMapper();
        ReceivableDto request = objectMapper.readValue(record.value(), ReceivableDto.class);
        receivableService.createReceivable(request);
    }

    @KafkaListener(topics = "update-receivable", groupId = "receivable-consumer-group")
    public void update(ConsumerRecord<String, String> record) throws JsonProcessingException {
        log.info(readHeader(record, "status"));
        ObjectMapper objectMapper = new ObjectMapper();
        FinanceReceivableDto request = objectMapper.readValue(record.value(), FinanceReceivableDto.class);
        receivableService.financeReceivable(request);
    }
}
