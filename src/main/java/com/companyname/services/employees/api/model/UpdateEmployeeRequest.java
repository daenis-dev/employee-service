package com.companyname.services.employees.api.model;

import com.companyname.services.core.errorhandling.InvalidRequestException;
import com.companyname.services.core.inputvalidation.EmailAddressValidator;
import lombok.Getter;

@Getter
public final class UpdateEmployeeRequest {

    private long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String jobTitle;
    private double salary;

    public UpdateEmployeeRequest withId(String id) {
        if (id == null || id.isEmpty()) {
            throw new InvalidRequestException("ID required for employee");
        }
        boolean theIdContainsNonNumericCharacters = !id.matches("\\d*");
        if (theIdContainsNonNumericCharacters) {
            throw new InvalidRequestException("ID value must be numeric for employee");
        }
        this.id = Long.parseLong(id);
        return this;
    }

    public UpdateEmployeeRequest withFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new InvalidRequestException("First name required for employee");
        }
        this.firstName = firstName;
        return this;
    }

    public UpdateEmployeeRequest withLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new InvalidRequestException("Last name required for employee");
        }
        this.lastName = lastName;
        return this;
    }

    public UpdateEmployeeRequest withEmailAddress(String emailAddress) {
        if (emailAddress == null || emailAddress.isEmpty()) {
            throw new InvalidRequestException("Email address required for employee");
        }
        if (new EmailAddressValidator().isNotValidFormat(emailAddress)) {
            throw new InvalidRequestException("Email address must be in a valid format for employee");
        }
        this.emailAddress = emailAddress;
        return this;
    }

    public UpdateEmployeeRequest withJobTitle(String jobTitle) {
        if (jobTitle == null || jobTitle.isEmpty()) {
            throw new InvalidRequestException("Job title required for employee");
        }
        this.jobTitle = jobTitle;
        return this;
    }

    public UpdateEmployeeRequest withSalary(String salary) {
        if (salary == null || salary.isEmpty()) {
            throw new InvalidRequestException("Salary required for employee");
        }
        boolean theSalaryContainsNonNumericCharacters = !salary.matches("[\\d]\\d*[.][\\d]\\d*") && !salary.matches("[\\d]\\d*");
        if (theSalaryContainsNonNumericCharacters) {
            throw new InvalidRequestException("Salary value must be numeric for employee");
        }
        this.salary = Double.parseDouble(salary);
        return this;
    }
}
