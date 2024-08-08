package com.example.commons.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FinanceDto {
    private Long id;
    private Long companyId;
    private Long receivableId;
    private BigDecimal amount;
}