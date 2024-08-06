package com.companyname.services.core.accounts.api.model;

import com.companyname.services.core.errorhandling.InvalidRequestException;
import com.companyname.services.employees.api.model.CreateEmployeeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    private LoginRequest loginRequest;

    @BeforeEach
    void init() {
        loginRequest = new LoginRequest();
    }

    @Test
    void setsEmailAddressForValidInput() {
        String theEmailAddress = "jim.recard@mail.com";

        LoginRequest theUpdatedRequest = loginRequest.withEmailAddress(theEmailAddress);

        String theEmailAddressFromTheUpdatedRequest = theUpdatedRequest.getEmailAddress();
        assertThat(theEmailAddressFromTheUpdatedRequest).isEqualTo(theEmailAddress);
    }

    @Test
    void doesNotSetTheEmailAddressForNullInput() {
        String thePredictedMessage = "Email address required for login";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> loginRequest.withEmailAddress(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheEmailAddressForEmptyInput() {
        String thePredictedMessage = "Email address required for login";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> loginRequest.withEmailAddress(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheEmailAddressForInvalidInput() {
        String thePredictedMessage = "Email address must be in a valid format for login";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> loginRequest.withEmailAddress("jim.recard.mail.com"));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void setsPasswordForValidInput() {
        String thePassword = "some-password";

        LoginRequest theUpdatedRequest = loginRequest.withPassword(thePassword);

        String thePasswordFromTheUpdatedRequest = theUpdatedRequest.getPassword();
        assertThat(thePasswordFromTheUpdatedRequest).isEqualTo(thePassword);
    }

    @Test
    void doesNotSetThePasswordForNullInput() {
        String thePredictedMessage = "Password required for login";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> loginRequest.withPassword(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetThePasswordForEmptyInput() {
        String thePredictedMessage = "Password required for login";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> loginRequest.withPassword(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }
}