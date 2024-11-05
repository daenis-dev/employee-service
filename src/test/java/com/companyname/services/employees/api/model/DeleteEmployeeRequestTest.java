package com.companyname.services.employees.api.model;

import com.companyname.services.core.errorhandling.InvalidRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DeleteEmployeeRequestTest {

    private DeleteEmployeeRequest deleteEmployeeRequest;

    @BeforeEach
    void init() {
        deleteEmployeeRequest = new DeleteEmployeeRequest();
    }

    @Test
    void setsIdForValidInput() {
        String theId = "2222";

        DeleteEmployeeRequest theUpdatedRequest = deleteEmployeeRequest.withId(theId);

        Long theIdFromTheUpdatedRequest = theUpdatedRequest.getId();
        assertThat(theIdFromTheUpdatedRequest).isEqualTo(Long.parseLong(theId));
    }

    @Test
    void doesNotSetTheIdForNullInput() {
        String thePredictedMessage = "ID required to delete employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> deleteEmployeeRequest.withId(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheIdForEmptyInput() {
        String thePredictedMessage = "ID required to delete employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> deleteEmployeeRequest.withId(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheIdForNonNumericData() {
        String thePredictedMessage = "ID value must be numeric to delete employee";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> deleteEmployeeRequest.withId("dd33"));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }
}