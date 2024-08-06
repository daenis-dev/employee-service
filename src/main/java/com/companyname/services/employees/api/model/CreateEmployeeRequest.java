package com.companyname.services.employees.api.model;

import com.companyname.services.core.errorhandling.InvalidRequestException;
import com.companyname.services.core.inputvalidation.EmailAddressValidator;
import lombok.Getter;

@Getter
public final class CreateEmployeeRequest {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String jobTitle;
    private String company;
    private double salary;

    public CreateEmployeeRequest withFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new InvalidRequestException("First name required for new employee");
        }
        this.firstName = firstName;
        return this;
    }

    public CreateEmployeeRequest withLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new InvalidRequestException("Last name required for new employee");
        }
        this.lastName = lastName;
        return this;
    }

    public CreateEmployeeRequest withEmailAddress(String emailAddress) {
        if (emailAddress == null || emailAddress.isEmpty()) {
            throw new InvalidRequestException("Email address required for new employee");
        }
        if (new EmailAddressValidator().isNotValidFormat(emailAddress)) {
            throw new InvalidRequestException("Email address must be in a valid format for new employee");
        }
        this.emailAddress = emailAddress;
        return this;
    }

    public CreateEmployeeRequest withJobTitle(String jobTitle) {
        if (jobTitle == null || jobTitle.isEmpty()) {
            throw new InvalidRequestException("Job title required for new employee");
        }
        this.jobTitle = jobTitle;
        return this;
    }

    public CreateEmployeeRequest withCompany(String company) {
        if (company == null || company.isEmpty()) {
            throw new InvalidRequestException("Company required for new employee");
        }
        this.company = company;
        return this;
    }

    public CreateEmployeeRequest withSalary(String salary) {
        if (salary == null || salary.isEmpty()) {
            throw new InvalidRequestException("Salary required for new employee");
        }
        boolean theSalaryContainsNonNumericCharacters = !salary.matches("[\\d]\\d*[.][\\d]\\d*") && !salary.matches("[\\d]\\d*");
        if (theSalaryContainsNonNumericCharacters) {
            throw new InvalidRequestException("Salary value must be numeric for new employee");
        }
        this.salary = Double.parseDouble(salary);
        return this;
    }
}
