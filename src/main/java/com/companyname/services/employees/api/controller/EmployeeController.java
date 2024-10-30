package com.companyname.services.employees.api.controller;

import com.companyname.services.employees.api.behavior.CreateEmployee;
import com.companyname.services.employees.api.behavior.DeleteEmployee;
import com.companyname.services.employees.api.behavior.FindAllEmployees;
import com.companyname.services.employees.api.behavior.UpdateEmployee;
import com.companyname.services.employees.api.model.CreateEmployeeRequest;
import com.companyname.services.employees.api.model.DeleteEmployeeRequest;
import com.companyname.services.employees.api.model.EmployeeDetails;
import com.companyname.services.employees.api.model.UpdateEmployeeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
public final class EmployeeController {

    private final CreateEmployee createEmployee;
    private final FindAllEmployees findAllEmployees;
    private final UpdateEmployee updateEmployee;
    private final DeleteEmployee deleteEmployee;

    @PostMapping("/v1/employees")
    public ResponseEntity<EmployeeDetails> createEmployee(
            @RequestParam("first-name") String firstName,
            @RequestParam("last-name") String lastName,
            @RequestParam("email-address") String emailAddress,
            @RequestParam("job-title") String jobTitle,
            @RequestParam("salary") String salary) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createEmployee.executeFor(new CreateEmployeeRequest()
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withEmailAddress(emailAddress)
                        .withJobTitle(jobTitle)
                        .withSalary(salary)));
    }

    @GetMapping("/v1/employees")
    public ResponseEntity<List<EmployeeDetails>> findAll() {
        return ResponseEntity.ok(findAllEmployees.execute());
    }

    @PutMapping("/v1/employees/{id}")
    public ResponseEntity<EmployeeDetails> updateEmployee(@PathVariable("id") String id,
                                                          @RequestParam("first-name") String firstName,
                                                          @RequestParam("last-name") String lastName,
                                                          @RequestParam("email-address") String emailAddress,
                                                          @RequestParam("job-title") String jobTitle,
                                                          @RequestParam("salary") String salary) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(updateEmployee.executeFor(new UpdateEmployeeRequest()
                        .withId(id)
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withEmailAddress(emailAddress)
                        .withJobTitle(jobTitle)
                        .withSalary(salary)));
    }

    // TODO: test
    @DeleteMapping("/v1/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") String id) {
        deleteEmployee.executeFor(new DeleteEmployeeRequest().withId(id));
        return ResponseEntity.ok().build();
    }
}
