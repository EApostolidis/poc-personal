package com.example.receivable.service;

import com.example.commons.model.FinanceReceivableDto;
import com.example.commons.model.ReceivableDto;
import com.example.receivable.model.ReceivableEntity;
import com.example.receivable.repository.ReceivableEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReceivableService {

    private final ReceivableEntityRepository receivableEntityRepository;

    @Transactional(readOnly = true)
    public ReceivableDto getReceivableById(Long id) {
        ReceivableEntity entity = getEntityById(id);
        return ReceivableDto.builder()
                .balance(entity.getBalance())
                .companyId(entity.getCompanyId())
                .id(entity.getId())
                .status(entity.getStatus())
                .build();
    }

    @Transactional(readOnly = true)
    public List<ReceivableDto> getReceivablesByCompanyId(Long id) {
        List<ReceivableEntity> receivableEntityList = receivableEntityRepository.findReceivableEntityByCompanyId(id);
        return receivableEntityList.stream()
                .map(entity -> ReceivableDto.builder()
                        .balance(entity.getBalance())
                        .companyId(entity.getCompanyId())
                        .id(entity.getId())
                        .status(entity.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public void createReceivable(ReceivableDto receivableDto) {
        ReceivableEntity entity = new ReceivableEntity();
        entity.setBalance(receivableDto.getBalance());
        entity.setStatus(receivableDto.getStatus());
        entity.setCompanyId(receivableDto.getCompanyId());
        receivableEntityRepository.save(entity);
    }

    @Transactional
    public void financeReceivable(FinanceReceivableDto financeReceivableDto) {
        ReceivableEntity entity = getEntityById(financeReceivableDto.getReceivableId());
        if (financeReceivableDto.getAmount().compareTo(entity.getBalance()) > 0) {
            throw new RuntimeException("The amount is bigger than receivables balance");
        }
        entity.setBalance(entity.getBalance().add(financeReceivableDto.getAmount()));
        receivableEntityRepository.save(entity);
    }

    private ReceivableEntity getEntityById(Long id) {
        return receivableEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receivable not found for " + id));

    }
}
