package com.example.company.controller;

import com.example.commons.model.CompanyDto;
import com.example.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.commons.constant.CommonConstant.CORRELATION_ID;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@Slf4j
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping(value ="/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long id , @RequestHeader HttpHeaders headers) {
        log.info("x-correlation-id : {}", headers.get(CORRELATION_ID));
        CompanyDto responseBody = companyService.getCompanyById(id);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }


}

