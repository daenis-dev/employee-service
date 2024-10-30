package com.companyname.services.employees.api.controller;

import com.companyname.services.employees.api.behavior.FindAllCompanies;
import com.companyname.services.employees.api.behavior.FindAllJobTitles;
import com.companyname.services.employees.api.model.CompanyDetails;
import com.companyname.services.employees.api.model.JobTitleDetails;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// TODO: test
@RestController
@AllArgsConstructor
public class CompanyController {

    private final FindAllCompanies findAllCompanies;

    @GetMapping("/v1/companies")
    public ResponseEntity<List<CompanyDetails>> findAllCompanies() {
        return ResponseEntity.ok(findAllCompanies.findAllCompanies());
    }
}
