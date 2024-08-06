package com.companyname.services.employees.api.model;

import com.companyname.services.core.errorhandling.InvalidRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CreateEmployeeRequestTest {

    private CreateEmployeeRequest createEmployeeRequest;

    @BeforeEach
    void init() {
        createEmployeeRequest = new CreateEmployeeRequest();
    }

    @Test
    void setsFirstNameForValidInput() {
        String theFirstName = "Jimmy";

        CreateEmployeeRequest theUpdatedRequest = createEmployeeRequest.withFirstName(theFirstName);

        String theFirstNameFromTheUpdatedRequest = theUpdatedRequest.getFirstName();
        assertThat(theFirstNameFromTheUpdatedRequest).isEqualTo(theFirstName);
    }

    @Test
    void doesNotSetTheFirstNameForNullInput() {
        String thePredictedMessage = "First name required for new employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> createEmployeeRequest.withFirstName(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheFirstNameForEmptyInput() {
        String thePredictedMessage = "First name required for new employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> createEmployeeRequest.withFirstName(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void setsLastNameForValidInput() {
        String theLastName = "Recard";

        CreateEmployeeRequest theUpdatedRequest = createEmployeeRequest.withLastName(theLastName);

        String theLastNameFromTheUpdatedRequest = theUpdatedRequest.getLastName();
        assertThat(theLastNameFromTheUpdatedRequest).isEqualTo(theLastName);
    }

    @Test
    void doesNotSetTheLastNameForNullInput() {
        String thePredictedMessage = "Last name required for new employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> createEmployeeRequest.withLastName(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheLastNameForEmptyInput() {
        String thePredictedMessage = "Last name required for new employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> createEmployeeRequest.withLastName(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void setsEmailAddressForValidInput() {
        String theEmailAddress = "jim.recard@mail.com";

        CreateEmployeeRequest theUpdatedRequest = createEmployeeRequest.withEmailAddress(theEmailAddress);

        String theEmailAddressFromTheUpdatedRequest = theUpdatedRequest.getEmailAddress();
        assertThat(theEmailAddressFromTheUpdatedRequest).isEqualTo(theEmailAddress);
    }

    @Test
    void doesNotSetTheEmailAddressForNullInput() {
        String thePredictedMessage = "Email address required for new employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> createEmployeeRequest.withEmailAddress(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheEmailAddressForEmptyInput() {
        String thePredictedMessage = "Email address required for new employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> createEmployeeRequest.withEmailAddress(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheEmailAddressForInvalidInput() {
        String thePredictedMessage = "Email address must be in a valid format for new employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> createEmployeeRequest.withEmailAddress("jim.recard.mail.com"));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void setsJobTitleForValidInput() {
        String theJobTitle = "Engineer";

        CreateEmployeeRequest theUpdatedRequest = createEmployeeRequest.withJobTitle(theJobTitle);

        String theJobTitleFromTheUpdatedRequest = theUpdatedRequest.getJobTitle();
        assertThat(theJobTitleFromTheUpdatedRequest).isEqualTo(theJobTitle);
    }

    @Test
    void doesNotSetTheJobTitleForNullInput() {
        String thePredictedMessage = "Job title required for new employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> createEmployeeRequest.withJobTitle(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheJobTitleForEmptyInput() {
        String thePredictedMessage = "Job title required for new employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> createEmployeeRequest.withJobTitle(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void setsSalaryForValidInputWithDecimal() {
        String theSalary = "100000.00";
        double thePredictedSalaryValue = Double.parseDouble(theSalary);

        CreateEmployeeRequest theUpdatedRequest = createEmployeeRequest.withSalary(theSalary);

        double theSalaryFromTheUpdatedRequest = theUpdatedRequest.getSalary();
        assertThat(theSalaryFromTheUpdatedRequest).isEqualTo(thePredictedSalaryValue);
    }

    @Test
    void setsSalaryForValidInputWithoutDecimal() {
        String theSalary = "100000";
        double thePredictedSalaryValue = Double.parseDouble(theSalary);

        CreateEmployeeRequest theUpdatedRequest = createEmployeeRequest.withSalary(theSalary);

        double theSalaryFromTheUpdatedRequest = theUpdatedRequest.getSalary();
        assertThat(theSalaryFromTheUpdatedRequest).isEqualTo(thePredictedSalaryValue);
    }

    @Test
    void doesNotSetTheSalaryForNullInput() {
        String thePredictedMessage = "Salary required for new employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> createEmployeeRequest.withSalary(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheSalaryForEmptyInput() {
        String thePredictedMessage = "Salary required for new employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> createEmployeeRequest.withSalary(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheSalaryForNonNumericInput() {
        String thePredictedMessage = "Salary value must be numeric for new employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> createEmployeeRequest.withSalary("INVALID"));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }
}