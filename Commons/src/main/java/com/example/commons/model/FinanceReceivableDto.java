package com.example.commons.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FinanceReceivableDto {

    private Long companyId;
    private Long receivableId;
    private BigDecimal amount;
}
