package com.companyname.services.employees.api.behavior;

import com.companyname.services.employees.api.model.DeleteEmployeeRequest;

public interface DeleteEmployee {

    void executeFor(DeleteEmployeeRequest request);
}
