package com.example.receivable.controller;

import com.example.commons.model.ReceivableDto;
import com.example.receivable.service.ReceivableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receivable")
@RequiredArgsConstructor
@Slf4j
public class ReceivableController {

    private final ReceivableService receivableService;

    @GetMapping(value ="/{id}")
    public ResponseEntity<ReceivableDto> getCompanyById(@PathVariable Long id) {
        ReceivableDto responseBody = receivableService.getReceivableById(id);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping(value ="/company/{id}")
    public ResponseEntity<List<ReceivableDto>> getReceivablesByCompanyId(@PathVariable Long id, @Header String test) {
        log.info(test);
        List<ReceivableDto> receivableDtoList = receivableService.getReceivablesByCompanyId(id);
        return new ResponseEntity<>(receivableDtoList, HttpStatus.OK);
    }

}
