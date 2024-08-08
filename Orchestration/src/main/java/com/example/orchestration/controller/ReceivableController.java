package com.example.orchestration.controller;

import com.example.commons.model.CompanyReceivablesDto;
import com.example.commons.model.ReceivableDto;
import com.example.orchestration.channel.ReceivableMessagingGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orchestration/receivable")
@RequiredArgsConstructor
public class ReceivableController {

    private final ReceivableMessagingGateway receivableMessagingGateway;

    @GetMapping(value ="/{id}")
    public ResponseEntity<ReceivableDto> getReceivableById(@PathVariable Long id) {
        return receivableMessagingGateway.requestReceivable(id);
    }

    @GetMapping(value ="/company/{id}")
    public ResponseEntity<CompanyReceivablesDto> getReceivablesByCompanyId(@PathVariable Long id) {
        return receivableMessagingGateway.requestCompanyReceivables(id);
    }

    @PostMapping
    public ResponseEntity<ReceivableDto> createReceivable(@RequestBody ReceivableDto receivableDto) {
        receivableMessagingGateway.saveReceivable(receivableDto);
        return ResponseEntity.accepted().build();
    }
}
