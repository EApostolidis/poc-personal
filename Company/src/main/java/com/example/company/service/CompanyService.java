package com.example.company.service;

import com.example.commons.model.CompanyDto;
import com.example.company.model.CompanyEntity;
import com.example.company.repository.CompanyEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyEntityRepository companyEntityRepository;

    @Transactional(readOnly = true)
    public CompanyDto getCompanyById(Long id) {
        CompanyEntity entity = companyEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with ID: " + id));

        return CompanyDto.builder()
                .id(entity.getId())
                .lastName(entity.getLastName())
                .firstName(entity.getFirstName())
                .build();
    }

    @Transactional
    public void createCompany(CompanyDto companyDto) {
        CompanyEntity entity = new CompanyEntity();
        entity.setFirstName(companyDto.getFirstName());
        entity.setLastName(companyDto.getLastName());
        companyEntityRepository.save(entity);
    }
}
