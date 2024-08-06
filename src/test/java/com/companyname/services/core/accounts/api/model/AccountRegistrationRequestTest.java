package com.companyname.services.core.accounts.api.model;

import com.companyname.services.core.errorhandling.InvalidRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AccountRegistrationRequestTest {

    private AccountRegistrationRequest accountRegistrationRequest;

    @BeforeEach
    void init() {
        accountRegistrationRequest = new AccountRegistrationRequest();
    }

    @Test
    void setsFirstNameForValidInput() {
        String theFirstName = "Jimmy";

        AccountRegistrationRequest theUpdatedRequest = accountRegistrationRequest.withFirstName(theFirstName);

        String theFirstNameFromTheUpdatedRequest = theUpdatedRequest.getFirstName();
        assertThat(theFirstNameFromTheUpdatedRequest).isEqualTo(theFirstName);
    }

    @Test
    void doesNotSetTheFirstNameForNullInput() {
        String thePredictedMessage = "First name required for new account";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> accountRegistrationRequest.withFirstName(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheFirstNameForEmptyInput() {
        String thePredictedMessage = "First name required for new account";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> accountRegistrationRequest.withFirstName(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void setsLastNameForValidInput() {
        String theLastName = "Recard";

        AccountRegistrationRequest theUpdatedRequest = accountRegistrationRequest.withLastName(theLastName);

        String theLastNameFromTheUpdatedRequest = theUpdatedRequest.getLastName();
        assertThat(theLastNameFromTheUpdatedRequest).isEqualTo(theLastName);
    }

    @Test
    void doesNotSetTheLastNameForNullInput() {
        String thePredictedMessage = "Last name required for new account";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> accountRegistrationRequest.withLastName(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheLastNameForEmptyInput() {
        String thePredictedMessage = "Last name required for new account";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> accountRegistrationRequest.withLastName(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void setsEmailAddressForValidInput() {
        String theEmailAddress = "jimmy.recard@jr.com";

        AccountRegistrationRequest theUpdatedRequest = accountRegistrationRequest.withEmailAddress(theEmailAddress);

        String theEmailAddressFromTheUpdatedRequest = theUpdatedRequest.getEmailAddress();
        assertThat(theEmailAddressFromTheUpdatedRequest).isEqualTo(theEmailAddress);
    }

    @Test
    void doesNotSetTheEmailAddressForNullInput() {
        String thePredictedMessage = "Email address required for new account";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> accountRegistrationRequest.withEmailAddress(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetTheEmailAddressForEmptyInput() {
        String thePredictedMessage = "Email address required for new account";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> accountRegistrationRequest.withEmailAddress(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void setsPasswordForValidInput() {
        String thePassword = "changeit";

        AccountRegistrationRequest theUpdatedRequest = accountRegistrationRequest.withPassword(thePassword);

        String thePasswordFromTheUpdatedRequest = theUpdatedRequest.getPassword();
        assertThat(thePasswordFromTheUpdatedRequest).isEqualTo(thePassword);
    }

    @Test
    void doesNotSetThePasswordForNullInput() {
        String thePredictedMessage = "Password required for new account";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> accountRegistrationRequest.withPassword(null));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void doesNotSetThePasswordForEmptyInput() {
        String thePredictedMessage = "Password required for new account";

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> accountRegistrationRequest.withPassword(""));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }

    @Test
    void confirmPasswordDoesNothingIfPasswordsMatch() {
        String thePassword = "changeit";
        accountRegistrationRequest.withPassword(thePassword);

        accountRegistrationRequest.confirmPassword(thePassword);
    }

    @Test
    void confirmPasswordThrowsAnExceptionIfPasswordsDoNotMatch() {
        String thePredictedMessage = "Passwords do not match for new account";
        accountRegistrationRequest.withPassword("changeit_1");

        InvalidRequestException theException = assertThrows(InvalidRequestException.class, () -> accountRegistrationRequest.confirmPassword("changeit_2"));

        String theMessageFromTheException = theException.getMessage();
        assertThat(theMessageFromTheException).isEqualTo(thePredictedMessage);
    }
}