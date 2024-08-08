package com.example.account.service;

import com.example.commons.configuration.RestTemplateComponent;
import com.example.commons.model.CommonResponseDto;
import com.example.commons.model.CompanyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.commons.constant.CommonConstant.CORRELATION_ID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyService {

    private static final String COMPANY_URL = "http://localhost:8083/company/";
    private final RestTemplateComponent restTemplateComponent;
    private static final ParameterizedTypeReference<CompanyDto> RESPONSE_TYPE = new ParameterizedTypeReference<>() {
    };

    public CompanyDto getCompanyById(Long companyId) {
        log.info("getCompanyById call");
        HttpHeaders headers = new HttpHeaders();
        final String correlationID = org.slf4j.MDC.get(CORRELATION_ID);
        headers.add(CORRELATION_ID, correlationID);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String url = COMPANY_URL + companyId;
        CommonResponseDto<Object> responseDto = restTemplateComponent.restCall(url, HttpMethod.GET, entity,RESPONSE_TYPE);
        if (responseDto.getErrorDetails() != null) {
            log.error("getCompanyById failed with  error {}",  responseDto.getErrorDetails());
            throw new RuntimeException("getCompanyById failed with  error " + responseDto.getErrorDetails());
        }
        return (CompanyDto) Optional.of(responseDto).map(CommonResponseDto::getData).orElse(null);
    }
}
