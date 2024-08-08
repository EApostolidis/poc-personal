package com.example.orchestration.controller;

import com.example.commons.model.CommonResponseDto;
import com.example.commons.model.CompanyDto;
import com.example.orchestration.channel.CompanyMessagingGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.*;

import static com.example.commons.constant.CommonConstant.CORRELATION_ID;

@RestController
@RequestMapping("/orchestration/company")
@RequiredArgsConstructor
@Slf4j
public class CompanyController {

    private final CompanyMessagingGateway companyMessagingGateway;

    @GetMapping(value ="/{id}")
    public ResponseEntity<CommonResponseDto<Object>> getCompanyById(@PathVariable Long id) {
        final String correlationID = org.slf4j.MDC.get(CORRELATION_ID);
        log.info("X-Correlation-ID : " + correlationID);
        Message<CommonResponseDto<Object>> response = companyMessagingGateway.requestCompany(id);
        return ResponseEntity.ok(response.getPayload());
    }

    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto) {
        companyMessagingGateway.saveCompany(companyDto);
        return ResponseEntity.accepted().build();
    }
}
