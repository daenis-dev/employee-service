package it.employees;

import com.companyname.services.employees.api.behavior.CreateEmployee;
import com.companyname.services.employees.api.model.CreateEmployeeRequest;
import com.companyname.services.employees.api.model.EmployeeDetails;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CreateEmployeeIT {

    @Autowired
    private CreateEmployee createEmployee;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void createsEmployee() {
        String theFirstName = "Jimmy";
        String theLastName = "Recard";
        String theEmailAddress = "jim.recard@mail.com";
        String theJobTitle = "Software Engineer";
        String theCompany = "Example Company LLC";
        String theSalary = "200000.00";
        EmployeeDetails theReturnedDetails = createEmployee.executeFor(new CreateEmployeeRequest()
                .withFirstName(theFirstName)
                .withLastName(theLastName)
                .withEmailAddress(theEmailAddress)
                .withJobTitle(theJobTitle)
                .withCompany(theCompany)
                .withSalary(theSalary));

        assertThat(theReturnedDetails.getFirstName()).isEqualTo(theFirstName);
        assertThat(theReturnedDetails.getLastName()).isEqualTo(theLastName);
        assertThat(theReturnedDetails.getEmailAddress()).isEqualTo(theEmailAddress);
        assertThat(theReturnedDetails.getJobTitle()).isEqualTo(theJobTitle);
        assertThat(theReturnedDetails.getCompany()).isEqualTo(theCompany);
        assertThat(theReturnedDetails.getSalary()).isEqualTo(Double.parseDouble(theSalary));
    }

    @AfterEach
    void deleteEmployeeForTest() {
        entityManager.createNativeQuery("SELECT setval('employees_id_seq', (SELECT COALESCE(MAX(id), 1) FROM employees) - 1)").getSingleResult();

        entityManager.createQuery("DELETE FROM Employee e WHERE e.firstName = :firstName AND e.lastName = :lastName")
                .setParameter("firstName", "Jimmy")
                .setParameter("lastName", "Recard")
                .executeUpdate();
    }
}
