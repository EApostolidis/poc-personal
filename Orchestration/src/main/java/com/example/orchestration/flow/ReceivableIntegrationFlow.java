package com.example.orchestration.flow;

import com.example.orchestration.channel.CompanyMessagingGateway;
import com.example.orchestration.service.ReceivableProducerService;
import com.example.orchestration.service.ReceivableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReceivableIntegrationFlow {

    private final MessageChannel receivableCreateRequestChannel;
    private final MessageChannel receivableRequestChannel;
    private final MessageChannel companyReceivablesRequestChannel;
    private final CompanyMessagingGateway companyMessagingGateway;
    private final ReceivableService receivableService;
    private final ReceivableProducerService receivableProducerService;


    @Bean
    public IntegrationFlow processReceivableRequest() {
        return IntegrationFlow.from(receivableRequestChannel)
                .handle(receivableService, "getReceivableByIdRestCall")
                .get();
    }


    @Bean
    public IntegrationFlow processReceivableCreateRequest() {
        return IntegrationFlow.from(receivableCreateRequestChannel)
                .handle(receivableProducerService, "sendMessage")
                .get();
    }

    @Bean
    public IntegrationFlow processCompanyReceivablesRequest() {
        return IntegrationFlow.from(companyReceivablesRequestChannel)
                .log("companyRequestChannel call")
                .handle((p, h) -> companyMessagingGateway.getCompanyById((Long) p))
                .log("getReceivablesByCompanyId call")
                .handle(receivableService, "getReceivablesByCompanyId")
                .log("getReceivablesByCompanyId exit")
                .get();
    }
}
