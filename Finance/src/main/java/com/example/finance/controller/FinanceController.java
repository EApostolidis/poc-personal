package com.example.finance.controller;

import com.example.commons.model.FinanceDto;
import com.example.finance.service.FinanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.commons.constant.CommonConstant.CORRELATION_ID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/finance")
@Slf4j
public class FinanceController {

    private final FinanceService financeService;

    @GetMapping(value ="/{id}")
    public ResponseEntity<FinanceDto> getFinanceById(@PathVariable Long id , @RequestHeader HttpHeaders headers) {
        log.info("x-correlation-id : " + headers.get(CORRELATION_ID));
        FinanceDto responseBody = financeService.getFinanceById(id);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
