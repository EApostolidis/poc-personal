package com.example.transactions.repository;

import com.example.transactions.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionEntityRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findAllByOrderByIdDesc();

}