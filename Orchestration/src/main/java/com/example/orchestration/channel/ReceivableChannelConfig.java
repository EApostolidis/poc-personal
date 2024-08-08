package com.example.orchestration.channel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class ReceivableChannelConfig {

    @Bean
    public MessageChannel receivableCreateRequestChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel receivableRequestChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel companyReceivablesRequestChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel companyReceivablesResponseChannel() {
        return new DirectChannel();
    }
}
