package com.companyname.services.employees.api.model;

import com.companyname.services.core.errorhandling.InvalidRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UpdateEmployeeRequestTest {

    private UpdateEmployeeRequest updateEmployeeRequest;

    @BeforeEach
    void init() {
        updateEmployeeRequest = new UpdateEmployeeRequest();
    }

    @Test
    void setsIdForValidInput() {
        String theId = "2222";

        UpdateEmployeeRequest theUpdatedRequest = updateEmployeeRequest.withId(theId);

        Long theIdFromTheUpdatedRequest = theUpdatedRequest.getId();
        assertThat(theIdFromTheUpdatedRequest).isEqualTo(Long.parseLong(theId));
    }

    @Test
    void doesNotSetTheIdForNullInput() {
        String thePredictedMessage = "ID required to update employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> updateEmployeeRequest.withId(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheIdForEmptyInput() {
        String thePredictedMessage = "ID required to update employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> updateEmployeeRequest.withId(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheIdForNonNumericData() {
        String thePredictedMessage = "ID value must be numeric to update employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> updateEmployeeRequest.withId("dd33"));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void setsFirstNameForValidInput() {
        String theFirstName = "Jimmy";

        UpdateEmployeeRequest theUpdatedRequest = updateEmployeeRequest.withFirstName(theFirstName);

        String theFirstNameFromTheUpdatedRequest = theUpdatedRequest.getFirstName();
        assertThat(theFirstNameFromTheUpdatedRequest).isEqualTo(theFirstName);
    }

    @Test
    void doesNotSetTheFirstNameForNullInput() {
        String thePredictedMessage = "First name required to update employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> updateEmployeeRequest.withFirstName(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheFirstNameForEmptyInput() {
        String thePredictedMessage = "First name required to update employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> updateEmployeeRequest.withFirstName(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void setsLastNameForValidInput() {
        String theLastName = "Recard";

        UpdateEmployeeRequest theUpdatedRequest = updateEmployeeRequest.withLastName(theLastName);

        String theLastNameFromTheUpdatedRequest = theUpdatedRequest.getLastName();
        assertThat(theLastNameFromTheUpdatedRequest).isEqualTo(theLastName);
    }

    @Test
    void doesNotSetTheLastNameForNullInput() {
        String thePredictedMessage = "Last name required to update employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> updateEmployeeRequest.withLastName(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheLastNameForEmptyInput() {
        String thePredictedMessage = "Last name required to update employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> updateEmployeeRequest.withLastName(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void setsEmailAddressForValidInput() {
        String theEmailAddress = "jim.recard@mail.com";

        UpdateEmployeeRequest theUpdatedRequest = updateEmployeeRequest.withEmailAddress(theEmailAddress);

        String theEmailAddressFromTheUpdatedRequest = theUpdatedRequest.getEmailAddress();
        assertThat(theEmailAddressFromTheUpdatedRequest).isEqualTo(theEmailAddress);
    }

    @Test
    void doesNotSetTheEmailAddressForNullInput() {
        String thePredictedMessage = "Email address required to update employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> updateEmployeeRequest.withEmailAddress(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheEmailAddressForEmptyInput() {
        String thePredictedMessage = "Email address required to update employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> updateEmployeeRequest.withEmailAddress(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheEmailAddressForInvalidInput() {
        String thePredictedMessage = "Email address must be in a valid format to update employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> updateEmployeeRequest.withEmailAddress("jim.recard.mail.com"));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void setsJobTitleForValidInput() {
        String theJobTitle = "Engineer";

        UpdateEmployeeRequest theUpdatedRequest = updateEmployeeRequest.withJobTitle(theJobTitle);

        String theJobTitleFromTheUpdatedRequest = theUpdatedRequest.getJobTitle();
        assertThat(theJobTitleFromTheUpdatedRequest).isEqualTo(theJobTitle);
    }

    @Test
    void doesNotSetTheJobTitleForNullInput() {
        String thePredictedMessage = "Job title required to update employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> updateEmployeeRequest.withJobTitle(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheJobTitleForEmptyInput() {
        String thePredictedMessage = "Job title required to update employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> updateEmployeeRequest.withJobTitle(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void setsSalaryForValidInputWithDecimal() {
        String theSalary = "100000.00";
        double thePredictedSalaryValue = Double.parseDouble(theSalary);

        UpdateEmployeeRequest theUpdatedRequest = updateEmployeeRequest.withSalary(theSalary);

        double theSalaryFromTheUpdatedRequest = theUpdatedRequest.getSalary();
        assertThat(theSalaryFromTheUpdatedRequest).isEqualTo(thePredictedSalaryValue);
    }

    @Test
    void setsSalaryForValidInputWithoutDecimal() {
        String theSalary = "100000";
        double thePredictedSalaryValue = Double.parseDouble(theSalary);

        UpdateEmployeeRequest theUpdatedRequest = updateEmployeeRequest.withSalary(theSalary);

        double theSalaryFromTheUpdatedRequest = theUpdatedRequest.getSalary();
        assertThat(theSalaryFromTheUpdatedRequest).isEqualTo(thePredictedSalaryValue);
    }

    @Test
    void doesNotSetTheSalaryForNullInput() {
        String thePredictedMessage = "Salary required to update employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> updateEmployeeRequest.withSalary(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheSalaryForEmptyInput() {
        String thePredictedMessage = "Salary required to update employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> updateEmployeeRequest.withSalary(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheSalaryForNonNumericInput() {
        String thePredictedMessage = "Salary value must be numeric to update employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> updateEmployeeRequest.withSalary("INVALID"));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }
}