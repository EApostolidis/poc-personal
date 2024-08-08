package com.example.commons.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyReceivablesDto {

    private CompanyDto companyDto;
    private List<ReceivableDto> receivableDtoList;
}
