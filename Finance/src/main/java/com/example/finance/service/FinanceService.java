package com.example.finance.service;

import com.example.commons.model.FinanceDto;
import com.example.finance.model.FinanceEntity;
import com.example.finance.repository.FinanceEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class FinanceService {

    private final FinanceEntityRepository financeEntityRepository;

    @Transactional(readOnly = true)
    public FinanceDto getFinanceById(Long id) {
        FinanceEntity entity = financeEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no finance with id " + id));
        return FinanceDto.builder()
                .amount(entity.getAmount())
                .receivableId(entity.getReceivableId())
                .companyId(entity.getCompanyId())
                .id(entity.getId())
                .build();
    }

    @Transactional
    public void createFinance(FinanceDto financeDto) {
        FinanceEntity entity = new FinanceEntity();
        entity.setAmount(financeDto.getAmount());
        entity.setCompanyId(financeDto.getCompanyId());
        entity.setReceivableId(entity.getReceivableId());
        financeEntityRepository.save(entity);
    }

}
