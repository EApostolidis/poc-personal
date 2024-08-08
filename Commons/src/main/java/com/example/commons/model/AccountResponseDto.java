package com.example.commons.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDto {
    private Long id;
    private Long companyId;
    private BigDecimal balance;
    private LocalDateTime latestUpdateTime;
    private Boolean active;
}