package com.example.account.controller;

import com.example.commons.model.AccountDto;
import com.example.account.model.AccountEntity;
import com.example.account.service.AccountService;
import com.example.commons.model.AccountResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.commons.constant.CommonConstant.CORRELATION_ID;


@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService service;

    @PostMapping
    public ResponseEntity<AccountEntity> createAccount(@RequestBody AccountDto account, @RequestHeader HttpHeaders headers) {
        log.info("x-correlation-id : {}", headers.get(CORRELATION_ID));
        AccountEntity entity = service.create(account);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountResponseDto> getCompanyById(@PathVariable Long id, @RequestHeader HttpHeaders headers) {
        log.info("x-correlation-id : {}", headers.get(CORRELATION_ID));
        AccountResponseDto responseBody = service.getAccountById(id);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
