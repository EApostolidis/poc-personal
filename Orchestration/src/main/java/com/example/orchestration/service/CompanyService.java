package com.example.orchestration.service;

import com.example.commons.model.CommonResponseDto;
import com.example.commons.model.CompanyDto;
import com.example.commons.configuration.RestTemplateComponent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static com.example.commons.constant.CommonConstant.CORRELATION_ID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyService {
    private static final String COMPANY_URL = "http://localhost:8083/company/";
    private final RestTemplateComponent restTemplateComponent;
    private static final ParameterizedTypeReference<CompanyDto> RESPONSE_TYPE = new ParameterizedTypeReference<>() {
    };
    public Message<CommonResponseDto<Object>> getCompanyById(Message<Long> message) {
        log.info("getCompanyById call");
        HttpHeaders headers = new HttpHeaders();
        final String correlationID = org.slf4j.MDC.get(CORRELATION_ID);
        headers.add(CORRELATION_ID, correlationID);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String url = COMPANY_URL + message.getPayload();
        CommonResponseDto<Object> responseDto = restTemplateComponent.restCall(url, HttpMethod.GET, entity,RESPONSE_TYPE);
        return MessageBuilder.withPayload(responseDto).build();
    }


}
