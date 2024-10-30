package com.companyname.services.employees.api;

import com.companyname.services.employees.api.behavior.CreateEmployee;
import com.companyname.services.employees.api.behavior.DeleteEmployee;
import com.companyname.services.employees.api.behavior.FindAllEmployees;
import com.companyname.services.employees.api.behavior.UpdateEmployee;
import com.companyname.services.employees.api.controller.EmployeeController;
import com.companyname.services.employees.api.model.EmployeeDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    private long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String jobTitle;
    private double salary;
    private EmployeeDetails employeeDetails;

    @Mock
    private CreateEmployee createEmployee;

    @Mock
    private FindAllEmployees findAllEmployees;

    @Mock
    private UpdateEmployee updateEmployee;

    @Mock
    private DeleteEmployee deleteEmployee;

    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        id = 1;
        firstName = "Jimmy";
        lastName = "Recard";
        emailAddress = "jimmy.recard@jr.com";
        jobTitle = "Engineer";
        salary = 100000.00;
        employeeDetails = new EmployeeDetails(1, firstName, lastName, emailAddress, jobTitle, salary);

        mockMvc = MockMvcBuilders
                .standaloneSetup(new EmployeeController(createEmployee, findAllEmployees, updateEmployee, deleteEmployee))
                .build();
    }

    @Test
    void createsEmployee() throws Exception {
        when(createEmployee.executeFor(any())).thenReturn(employeeDetails);

        mockMvc.perform(post("/v1/employees")
                        .param("first-name", firstName)
                        .param("last-name", lastName)
                        .param("email-address", emailAddress)
                        .param("job-title", jobTitle)
                        .param("salary", String.valueOf(salary)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is(firstName)))
                .andExpect(jsonPath("$.lastName", is(lastName)))
                .andExpect(jsonPath("$.emailAddress", is(emailAddress)))
                .andExpect(jsonPath("$.jobTitle", is(jobTitle)))
                .andExpect(jsonPath("$.salary", is(salary)));
    }

    @Test
    void findsAllEmployees() throws Exception {
        when(findAllEmployees.execute()).thenReturn(Collections.singletonList(employeeDetails));

        mockMvc.perform(get("/v1/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is(firstName)))
                .andExpect(jsonPath("$[0].lastName", is(lastName)))
                .andExpect(jsonPath("$[0].emailAddress", is(emailAddress)))
                .andExpect(jsonPath("$[0].jobTitle", is(jobTitle)))
                .andExpect(jsonPath("$[0].salary", is(salary)));;
    }

    @Test
    void updatesEmployee() throws Exception {
        when(updateEmployee.executeFor(any())).thenReturn(employeeDetails);

        mockMvc.perform(put("/v1/employees/1")
                        .param("first-name", "Jimmy")
                        .param("last-name", "Recard")
                        .param("email-address", "jimmy.recard@jr.com")
                        .param("job-title", "Engineer")
                        .param("salary", "100000.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is(firstName)))
                .andExpect(jsonPath("$.lastName", is(lastName)))
                .andExpect(jsonPath("$.emailAddress", is(emailAddress)))
                .andExpect(jsonPath("$.jobTitle", is(jobTitle)))
                .andExpect(jsonPath("$.salary", is(salary)));
    }
}
