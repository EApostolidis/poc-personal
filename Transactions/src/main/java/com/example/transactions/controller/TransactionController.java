package com.example.transactions.controller;

import com.example.commons.model.TransactionDto;
import com.example.transactions.model.TransactionEntity;
import com.example.transactions.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.commons.constant.CommonConstant.CORRELATION_ID;


@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;;

    @PostMapping
    public ResponseEntity<TransactionEntity> createTransaction(@RequestBody TransactionDto transactionDto) {
        String correlationId = org.slf4j.MDC.get(CORRELATION_ID);
        TransactionEntity response = transactionService.createTransaction(transactionDto, correlationId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(value ="cancel/{id}")
    public ResponseEntity<TransactionEntity> cancelTransaction(@PathVariable Long id) {
        String correlationId = org.slf4j.MDC.get(CORRELATION_ID);
        TransactionEntity response = transactionService.cancelTransaction(id, correlationId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping(value ="update/{id}")
    public ResponseEntity<TransactionEntity> updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        String correlationId = org.slf4j.MDC.get(CORRELATION_ID);
        TransactionEntity response = transactionService.updateTransaction(id, correlationId, transactionDto);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<TransactionEntity>> getTransactions() {
        List<TransactionEntity> response = transactionService.getAllTransactions();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
