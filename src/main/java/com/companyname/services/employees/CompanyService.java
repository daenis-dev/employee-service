package com.companyname.services.employees;

import com.companyname.services.employees.api.behavior.FindAllCompanies;
import com.companyname.services.employees.api.model.CompanyDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: IT
@Service
@RequiredArgsConstructor
class CompanyService implements FindAllCompanies {

    private final CompanyRepository companyRepository;

    @Override
    public List<CompanyDetails> findAllCompanies() {
        return companyRepository.findAllCompanyDetails();
    }
}
