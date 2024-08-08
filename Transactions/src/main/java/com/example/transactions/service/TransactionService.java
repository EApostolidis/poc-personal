package com.example.transactions.service;

import com.example.commons.model.TransactionAction;
import com.example.transactions.model.TransactionEntity;
import com.example.commons.model.TransactionDto;
import com.example.transactions.repository.TransactionEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final AccountService accountService;
    private final CompanyService companyService;
    private final TransactionEntityRepository repository;
    private final TransactionProducer producer;

    @Transactional
    public TransactionEntity createTransaction(TransactionDto transactionDto, String correlationId) {
        accountService.getAccountById(transactionDto.getAccountId());
        companyService.getCompanyById(transactionDto.getFromCompanyId());
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAccountId(transactionDto.getAccountId());
        transactionEntity.setActive(true);
        transactionEntity.setAmount(transactionDto.getAmount());
        transactionEntity.setFromCompanyId(transactionDto.getFromCompanyId());
        transactionEntity.setAction(TransactionAction.CREATE);
        transactionEntity.setDate(LocalDateTime.now());
        transactionEntity.setCorrelationId(correlationId);
        TransactionEntity created = repository.save(transactionEntity);
        TransactionDto createdTransactionDto = createTransactionDto(transactionEntity);
        producer.sendMessage(createdTransactionDto, correlationId);
        log.info("Transaction created: {}", created);
        return created;
    }

    @Transactional
    public TransactionEntity cancelTransaction(Long transactionId, String correlationId) {
        TransactionEntity transactionEntity = validateTransaction(transactionId);
        TransactionEntity revertTransactionEntity = new TransactionEntity();
        revertTransactionEntity.setAccountId(transactionEntity.getAccountId());
        revertTransactionEntity.setActive(true);
        revertTransactionEntity.setDate(LocalDateTime.now());
        revertTransactionEntity.setCorrelationId(correlationId);
        revertTransactionEntity.setAction(TransactionAction.CANCEL);
        revertTransactionEntity.setFromCompanyId(transactionEntity.getFromCompanyId());
        revertTransactionEntity.setAmount(transactionEntity.getAmount().negate());
        TransactionEntity cancelled = repository.save(revertTransactionEntity);
        transactionEntity.setAction(TransactionAction.CANCEL);
        repository.save(transactionEntity);
        TransactionDto cacncelledTransactionDto = createTransactionDto(cancelled);
        producer.sendMessage(cacncelledTransactionDto, correlationId);
        log.info("Transaction cancelled: {}", cancelled);
        return cancelled;
    }


    @Transactional
    public TransactionEntity updateTransaction(Long transactionId, String correlationId, TransactionDto transactionDto) {
        cancelTransaction(transactionId, correlationId);
        TransactionEntity updateTransactionEntity = new TransactionEntity();
        updateTransactionEntity.setAccountId(transactionDto.getAccountId());
        updateTransactionEntity.setActive(true);
        updateTransactionEntity.setDate(LocalDateTime.now());
        updateTransactionEntity.setCorrelationId(correlationId);
        updateTransactionEntity.setAction(TransactionAction.UPDATE);
        updateTransactionEntity.setFromCompanyId(transactionDto.getFromCompanyId());
        updateTransactionEntity.setAmount(transactionDto.getAmount());
        TransactionEntity updated = repository.save(updateTransactionEntity);
        TransactionDto updatedTransactionDto = createTransactionDto(updated);
        producer.sendMessage(updatedTransactionDto, correlationId);
        log.info("Transaction updated: {}", updated);
        return updated;
    }

    private TransactionEntity validateTransaction(Long transactionId) {
        TransactionEntity transactionEntity = repository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction with id " + transactionId + "  not found"));
        if (transactionEntity.getAction().equals(TransactionAction.CANCEL)) {
            throw new RuntimeException("Transaction with id " + transactionId + " is already cancelled");
        }
        return transactionEntity;
    }


    public List<TransactionEntity> getAllTransactions() {
        return repository.findAllByOrderByIdDesc();
    }


    private TransactionDto createTransactionDto(TransactionEntity transactionEntity) {
        return TransactionDto.builder()
                .accountId(transactionEntity.getAccountId())
                .fromCompanyId(transactionEntity.getFromCompanyId())
                .amount(transactionEntity.getAmount())
                .action(transactionEntity.getAction())
                .build();
    }
}
