package com.companyname.services.employees;

import com.companyname.services.employees.api.model.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT NEW com.companyname.services.employees.api.model.EmployeeDetails(e.id, e.firstName, e.lastName, e.emailAddress, jt.name, c.name, e.salary) FROM Employee e JOIN e.jobTitle jt JOIN e.company c")
    List<EmployeeDetails> findAllEmployeeDetails();
}
