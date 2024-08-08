package com.example.finance.repository;

import com.example.finance.model.FinanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceEntityRepository extends JpaRepository<FinanceEntity, Long> {
}