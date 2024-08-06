package it.employees;

import com.companyname.services.employees.api.behavior.UpdateEmployee;
import com.companyname.services.employees.api.model.EmployeeDetails;
import com.companyname.services.employees.api.model.UpdateEmployeeRequest;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UpdateEmployeeIT {

    @Autowired
    private UpdateEmployee updateEmployee;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void updatesEmployee() {
        String theId = "1";
        String theFirstName = "Don";
        String theLastName = "Joe";
        String theEmailAddress = "don.joe@mail.com";
        String theJobTitle = "Software Engineer";
        String theCompany = "Example Company LLC";
        String theSalary = "150000.00";
        UpdateEmployeeRequest theRequest = new UpdateEmployeeRequest()
                .withId(theId)
                .withFirstName(theFirstName)
                .withLastName(theLastName)
                .withEmailAddress(theEmailAddress)
                .withJobTitle(theJobTitle)
                .withCompany(theCompany)
                .withSalary(theSalary);

        EmployeeDetails theDetails = updateEmployee.executeFor(theRequest);

        assertThat(theDetails.getId()).isEqualTo(Long.parseLong(theId));
        assertThat(theDetails.getFirstName()).isEqualTo(theFirstName);
        assertThat(theDetails.getLastName()).isEqualTo(theLastName);
        assertThat(theDetails.getEmailAddress()).isEqualTo(theEmailAddress);
        assertThat(theDetails.getJobTitle()).isEqualTo(theJobTitle);
        assertThat(theDetails.getCompany()).isEqualTo(theCompany);
        assertThat(theDetails.getSalary()).isEqualTo(Double.parseDouble(theSalary));
    }

    @AfterEach
    void resetEmployeeForTest() {
        entityManager.createNativeQuery("UPDATE employees SET first_name = 'Jon' WHERE id = 1")
                .executeUpdate();
        entityManager.createNativeQuery("UPDATE employees SET last_name = 'Doe' WHERE id = 1")
                .executeUpdate();
        entityManager.createNativeQuery("UPDATE employees SET email_address = 'jon.doe@mail.com' WHERE id = 1")
                .executeUpdate();
        entityManager.createNativeQuery("UPDATE employees SET salary = 130000.00 WHERE id = 1")
                .executeUpdate();
    }
}
