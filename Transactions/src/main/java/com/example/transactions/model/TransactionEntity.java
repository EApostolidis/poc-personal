package com.example.transactions.model;

import com.example.commons.model.TransactionAction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    private BigDecimal amount;

    private LocalDateTime date;

    private Boolean active;

    private Long fromCompanyId;

    @Enumerated(EnumType.STRING)
    @Column(length = 255)
    private TransactionAction action;

    @Column(name = "correlation_id")
    private String correlationId;
}
