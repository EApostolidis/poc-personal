package com.example.orchestration.flow;

import com.example.orchestration.service.CompanyProducerService;
import com.example.orchestration.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CompanyIntegrationFlow {

    private final MessageChannel companyRequestChannel;
    private final MessageChannel companyCreateRequestChannel;
    private final CompanyService companyService;
    private final CompanyProducerService companyProducerService;


    @Bean
    public IntegrationFlow processCompanyRequest() {
        return IntegrationFlow.from(companyRequestChannel)
                .handle(companyService, "getCompanyById")
                .get();
    }


    @Bean
    public IntegrationFlow processCompanyCreateRequest() {
        return IntegrationFlow.from(companyCreateRequestChannel)
                .handle(companyProducerService, "sendMessage")
                .get();
    }
}
