package com.companyname.services.employees.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class EmployeeDetails {
    private long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String jobTitle;
    private double salary;
}
