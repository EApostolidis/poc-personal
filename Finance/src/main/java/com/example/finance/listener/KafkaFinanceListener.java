package com.example.finance.listener;

import com.example.commons.model.FinanceDto;
import com.example.finance.service.FinanceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaFinanceListener {

    private final FinanceService financeService;

    @KafkaListener(topics = "create-finance", groupId = "finance-consumer-group")
    public void listen(ConsumerRecord<String, String> record) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FinanceDto request = objectMapper.readValue(record.value(), FinanceDto.class);
        financeService.createFinance(request);
    }
}
