package com.example.orchestration.service;

import com.example.commons.model.CommonResponseDto;
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

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceivableProducerService {

    private final KafkaTemplate<String, Map<String, Object>> kafkaTemplate;
    private final CompanyService companyService;
    @Transactional
    public void sendMessage(Message<ReceivableDto> message) {
        ReceivableDto receivableDto = message.getPayload();
        Message<Long> companyMessage = MessageBuilder.withPayload(receivableDto.getCompanyId()).build();
        Message<CommonResponseDto<Object>> response = companyService.getCompanyById(companyMessage);
        if (response.getPayload().getErrorDetails() != null) {
            throw new RuntimeException(response.getPayload().getErrorDetails().getMessage());
        }
        Message<ReceivableDto> kakfkaMessage = MessageBuilder.withPayload(receivableDto)
                .setHeader(KafkaHeaders.TOPIC, "create-receivable")
                .setHeader("status", "CREATE")
                .build();
        kafkaTemplate.send(kakfkaMessage);
    }
}
