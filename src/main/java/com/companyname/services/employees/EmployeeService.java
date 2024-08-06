package com.companyname.services.employees;

import com.companyname.services.core.errorhandling.InvalidRequestException;
import com.companyname.services.employees.api.behavior.CreateEmployee;
import com.companyname.services.employees.api.behavior.FindAllEmployees;
import com.companyname.services.employees.api.behavior.UpdateEmployee;
import com.companyname.services.employees.api.model.CreateEmployeeRequest;
import com.companyname.services.employees.api.model.EmployeeDetails;
import com.companyname.services.employees.api.model.UpdateEmployeeRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
final class EmployeeService implements CreateEmployee, FindAllEmployees, UpdateEmployee {

    private final EmployeeRepository employeeRepository;
    private final JobTitleRepository jobTitleRepository;
    private final CompanyRepository companyRepository;

    @Override
    public EmployeeDetails executeFor(CreateEmployeeRequest theRequest) {
        Employee employee = new Employee();
        employee.setFirstName(theRequest.getFirstName());
        employee.setLastName(theRequest.getLastName());
        employee.setJobTitle(jobTitleRepository.findByName(theRequest.getJobTitle()).orElseThrow(() -> new InvalidRequestException("Please select a valid job title")));
        employee.setCompany(companyRepository.findByName(theRequest.getCompany()).orElseThrow(() -> new InvalidRequestException("Please select a valid company")));
        employee.setEmailAddress(theRequest.getEmailAddress());
        employee.setSalary(theRequest.getSalary());
        return employeeRepository.save(employee).getDetails();
    }

    @Override
    public List<EmployeeDetails> execute() {
        return employeeRepository.findAllEmployeeDetails();
    }

    @Override
    public EmployeeDetails executeFor(UpdateEmployeeRequest request) {
        Employee employee = employeeRepository.findById(request.getId()).orElseThrow(() -> new InvalidRequestException("Employee does not exist for update"));
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmailAddress(request.getEmailAddress());
        employee.setSalary(request.getSalary());
        employee.setJobTitle(jobTitleRepository.findByName(request.getJobTitle()).orElseThrow(() -> new InvalidRequestException("PLease select a valid job title")));
        employee.setCompany(companyRepository.findByName(request.getCompany()).orElseThrow(() -> new InvalidRequestException("Please select a valid company")));
        return employeeRepository.save(employee).getDetails();
    }
}
