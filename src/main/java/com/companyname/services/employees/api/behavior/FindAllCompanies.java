package com.companyname.services.employees.api.behavior;

import com.companyname.services.employees.api.model.CompanyDetails;
import com.companyname.services.employees.api.model.JobTitleDetails;

import java.util.List;

public interface FindAllCompanies {

    List<CompanyDetails> findAllCompanies();
}
