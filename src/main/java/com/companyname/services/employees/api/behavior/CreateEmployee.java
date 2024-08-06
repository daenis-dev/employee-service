package com.companyname.services.employees.api.behavior;

import com.companyname.services.employees.api.model.CreateEmployeeRequest;
import com.companyname.services.employees.api.model.EmployeeDetails;

public interface CreateEmployee {

    EmployeeDetails executeFor(CreateEmployeeRequest theRequest);
}
