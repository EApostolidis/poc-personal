package com.example.account.service;

import com.example.account.model.AccountEntity;
import com.example.account.repository.AccountEntityRepository;
import com.example.commons.model.AccountDto;
import com.example.commons.model.AccountResponseDto;
import com.example.commons.model.TransactionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountEntityRepository repository;
    private final CompanyService companyService;

    @Transactional
    public AccountEntity create(AccountDto accountDto) {
        companyService.getCompanyById(accountDto.getCompanyId());
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setBalance(accountDto.getBalance());
        accountEntity.setCompanyId(accountDto.getCompanyId());
        accountEntity.setLatestUpdateTime(LocalDateTime.now());
        accountEntity.setActive(true);
        AccountEntity entity = repository.save(accountEntity);
        log.info("Created account {}", entity);
        return entity;
    }

    @Transactional
    public void calculateAccountBalance(TransactionDto transactionDto) {
        AccountEntity entity = findById(transactionDto.getAccountId());
        entity.setBalance(entity.getBalance().add(transactionDto.getAmount()));
        entity.setLatestUpdateTime(LocalDateTime.now());
        repository.saveAndFlush(entity);
    }

    public AccountResponseDto getAccountById(Long id) {
        AccountEntity entity = findById(id);
        return AccountResponseDto.builder()
                .companyId(entity.getCompanyId())
                .balance(entity.getBalance())
                .id(entity.getId())
                .latestUpdateTime(entity.getLatestUpdateTime())
                .active(entity.getActive())
                .build();
    }

    private AccountEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Account not found with id " + id));
    }
}
