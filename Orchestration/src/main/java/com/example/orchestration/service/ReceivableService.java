package com.example.orchestration.service;

import com.example.commons.configuration.RestTemplateComponent;
import com.example.commons.model.CommonResponseDto;
import com.example.commons.model.CompanyDto;
import com.example.commons.model.CompanyReceivablesDto;
import com.example.commons.model.ReceivableDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceivableService {

    private static final String RECEIVABLE_URL = "http://localhost:8082/receivable/";
    private static final String RECEIVABLE_COMPANY_URL = "http://localhost:8082/receivable/company/";
    private final RestTemplateComponent restTemplateComponent;
    private static final ParameterizedTypeReference<ReceivableDto> RESPONSE_DTO = new ParameterizedTypeReference<>() {};

    private static final ParameterizedTypeReference<List<ReceivableDto>> RECEIVABLE_LIST_RESPONSE = new ParameterizedTypeReference<>() {};

    public  ResponseEntity<CommonResponseDto> getReceivableByIdRestCall(Message<Long> message) {
        log.info("getCompanyById rest call");
        String url = RECEIVABLE_URL + message.getPayload();
        var response = restTemplateComponent.restCall(url, HttpMethod.GET, null, RESPONSE_DTO);
        return ResponseEntity.ok(response);
    }


    public  Message<CommonResponseDto<Object>> getReceivableById(Message<Long> message) {
        log.info("getCompanyById call");
        String url = RECEIVABLE_URL + message.getPayload();
        var response = restTemplateComponent.restCall(url, HttpMethod.GET, null, RESPONSE_DTO);
        return MessageBuilder.withPayload(response).build();
    }

    public ResponseEntity<CompanyReceivablesDto> getReceivablesByCompanyId(Message<CommonResponseDto<CompanyDto>> message) {
        log.info("getReceivablesByCompanyId call");
        CommonResponseDto<CompanyDto> companyDto = message.getPayload(); // Directly use the payload
        String url = RECEIVABLE_COMPANY_URL + companyDto.getData().getId();
        List<ReceivableDto> receivableDtoList = (List<ReceivableDto>) restTemplateComponent.restCall(url, HttpMethod.GET, null, RECEIVABLE_LIST_RESPONSE).getData();
        CompanyReceivablesDto companyReceivablesDto = CompanyReceivablesDto.builder()
                .companyDto(companyDto.getData())
                .receivableDtoList(receivableDtoList)
                .build();
        return ResponseEntity.ok(companyReceivablesDto);
    }
}
