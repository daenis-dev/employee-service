package com.companyname.services.employees.api.behavior;

import com.companyname.services.employees.api.model.EmployeeDetails;
import com.companyname.services.employees.api.model.UpdateEmployeeRequest;

public interface UpdateEmployee {

    EmployeeDetails executeFor(UpdateEmployeeRequest request);
}
