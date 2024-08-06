package com.companyname.services.core.inputvalidation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmailAddressValidatorTest {

    private EmailAddressValidator emailAddressValidator;

    @BeforeEach
    void init() {
        emailAddressValidator = new EmailAddressValidator();
    }

    @Test
    void isNotAValidFormatIfTheEmailAddressIsNull() {
        boolean theEmailAddressIsNotValid = emailAddressValidator.isNotValidFormat(null);

        assertThat(theEmailAddressIsNotValid).isTrue();
    }

    @Test
    void isNotAValidFormatIfTheEmailAddressIsABlankString() {
        boolean theEmailAddressIsNotValid = emailAddressValidator.isNotValidFormat("");

        assertThat(theEmailAddressIsNotValid).isTrue();
    }

    @Test
    void isNotAValidFormatIfTheEmailAddressIsMissingTheCommercialAt() {
        boolean theEmailAddressIsNotValid = emailAddressValidator.isNotValidFormat("jon.doemail.com");

        assertThat(theEmailAddressIsNotValid).isTrue();
    }

    @Test
    void isNotAValidFormatIfTheEmailAddressContainsABackslash() {
        boolean theEmailAddressIsNotValid = emailAddressValidator.isNotValidFormat("jon\\doe@mail.com");

        assertThat(theEmailAddressIsNotValid).isTrue();
    }

    @Test
    void isNotAValidFormatIfTheEmailAddressIsMissingTheDomain() {
        boolean theEmailAddressIsNotValid = emailAddressValidator.isNotValidFormat("jon.doe@");

        assertThat(theEmailAddressIsNotValid).isTrue();
    }

    @Test
    void isAValidFormatIfTheEmailContainsValidCharactersAndACommercialAtAndADomain() {
        boolean theEmailAddressIsNotValid = emailAddressValidator.isNotValidFormat("jon.doe@mail.com");

        assertThat(theEmailAddressIsNotValid).isFalse();
    }
}