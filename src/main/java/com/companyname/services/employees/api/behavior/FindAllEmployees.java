package com.companyname.services.employees.api.behavior;

import com.companyname.services.employees.api.model.EmployeeDetails;

import java.util.List;

public interface FindAllEmployees {

    List<EmployeeDetails> execute();
}
