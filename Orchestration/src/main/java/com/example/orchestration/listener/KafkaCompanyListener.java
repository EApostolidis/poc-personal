package com.example.orchestration.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KafkaCompanyListener {

    @KafkaListener(topics = "company", groupId = "company-consumer-group")
    public void listen(ConsumerRecord<String, Map<String, Object>> record) {
        System.out.println("Received message headers: " + record.headers());
        System.out.println("Received message key: " + record.key());
        System.out.println("Received message body: " + record.value());
    }

}
