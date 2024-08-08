package com.example.orchestration.channel;

import com.example.commons.model.CompanyReceivablesDto;
import com.example.commons.model.ReceivableDto;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface ReceivableMessagingGateway {

    @Gateway(requestChannel = "receivableCreateRequestChannel")
    void saveReceivable(ReceivableDto receivableDto);

    @Gateway(requestChannel = "receivableRequestChannel")
    ResponseEntity<ReceivableDto> requestReceivable(Long id);

    @Gateway(requestChannel = "companyReceivablesRequestChannel", replyChannel = "companyReceivablesResponseChannel")
    ResponseEntity<CompanyReceivablesDto> requestCompanyReceivables(Long id);
}