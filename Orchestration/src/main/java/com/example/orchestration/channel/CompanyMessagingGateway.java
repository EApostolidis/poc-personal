package com.example.orchestration.channel;

import com.example.commons.model.CommonResponseDto;
import com.example.commons.model.CompanyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway
public interface CompanyMessagingGateway {

    @Gateway(requestChannel = "companyCreateRequestChannel")
    void saveCompany(CompanyDto companyDto);

    @Gateway(requestChannel = "companyRequestChannel")
    Message<CommonResponseDto<Object>> requestCompany(Long id);

    @Gateway(requestChannel = "companyRequestChannel", replyChannel = "companyResponseChannel")
    Message<CompanyDto> getCompanyById(Long id);
}