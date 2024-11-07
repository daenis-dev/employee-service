package com.companyname.services.employees;

import com.companyname.services.employees.api.model.EmployeeDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private long jobTitleId;
    private String jobTitleName;
    private JobTitle jobTitle;
    private double salary;

    private Employee employee;

    @BeforeEach
    void init() {
        id = 1L;
        firstName = "John";
        lastName = "Doe";
        emailAddress = "jon.doe@mail.com";
        jobTitleId = 1L;
        jobTitleName = "Sales";
        jobTitle = new JobTitle();
        jobTitle.setId(jobTitleId);
        jobTitle.setName(jobTitleName);
        salary = 200000.00;

        employee = new Employee();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmailAddress(emailAddress);
        employee.setJobTitle(jobTitle);
        employee.setSalary(salary);
    }

    @Test
    void employeesWithEqualPropertiesAreEqual() {
        Employee theOtherEmployee = new Employee();
        theOtherEmployee.setId(id);
        theOtherEmployee.setFirstName(firstName);
        theOtherEmployee.setLastName(lastName);
        theOtherEmployee.setEmailAddress(emailAddress);
        theOtherEmployee.setJobTitle(jobTitle);
        theOtherEmployee.setSalary(salary);

        boolean employeeEqualsTheOtherEmployee = employee.equals(theOtherEmployee);

        assertThat(employeeEqualsTheOtherEmployee).isTrue();
    }

    @Test
    void employeesWithoutEqualPropertiesAreNotEqual() {
        Employee theOtherEmployee = new Employee();
        theOtherEmployee.setId(2L);
        theOtherEmployee.setFirstName(firstName);
        theOtherEmployee.setLastName(lastName);
        theOtherEmployee.setEmailAddress(emailAddress);
        theOtherEmployee.setJobTitle(jobTitle);
        theOtherEmployee.setSalary(salary);

        boolean employeeEqualsTheOtherEmployee = employee.equals(theOtherEmployee);

        assertThat(employeeEqualsTheOtherEmployee).isFalse();
    }

    @Test
    void getsTheHashCode() {
        int theHashCode = employee.hashCode();

        assertThat(theHashCode).isEqualTo(-1716585482);
    }

    @Test
    void getsTheDetails() {
        EmployeeDetails theDetails = employee.getDetails();

        assertThat(theDetails.getId()).isEqualTo(id);
        assertThat(theDetails.getEmailAddress()).isEqualTo(emailAddress);
        assertThat(theDetails.getJobTitle()).isEqualTo(jobTitleName);
    }
}