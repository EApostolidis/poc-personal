package com.example.commons.utility;

import lombok.experimental.UtilityClass;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.nio.charset.StandardCharsets;

@UtilityClass
public class KafkaUtility {

    public static String readHeader(ConsumerRecord<String, String> record, String header) {
        return new String(record.headers().headers(header).iterator().next().value(), StandardCharsets.UTF_8);
    }
}
