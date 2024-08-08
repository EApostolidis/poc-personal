package com.example.orchestration.channel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class CompanyChannelConfig {

    @Bean
    public MessageChannel companyRequestChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel companyResponseChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel companyCreateRequestChannel() {
        return new DirectChannel();
    }
}
