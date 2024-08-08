package com.example.commons.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
    private Long accountId;
    private BigDecimal amount;
    private Long fromCompanyId;
    private TransactionAction action;
}