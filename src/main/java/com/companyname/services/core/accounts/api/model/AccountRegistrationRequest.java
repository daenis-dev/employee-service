package com.companyname.services.core.accounts.api.model;

import com.companyname.services.core.errorhandling.InvalidRequestException;
import com.companyname.services.core.inputvalidation.EmailAddressValidator;
import lombok.Getter;

@Getter
public final class AccountRegistrationRequest {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;

    public AccountRegistrationRequest withFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new InvalidRequestException("First name required for new account");
        }
        this.firstName = firstName;
        return this;
    }

    public AccountRegistrationRequest withLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new InvalidRequestException("Last name required for new account");
        }
        this.lastName = lastName;
        return this;
    }

    public AccountRegistrationRequest withEmailAddress(String emailAddress) {
        if (emailAddress == null || emailAddress.isEmpty()) {
            throw new InvalidRequestException("Email address required for new account");
        }
        // TODO: test email validation
        if (new EmailAddressValidator().isNotValidFormat(emailAddress)) {
            throw new InvalidRequestException("Email address must be in a valid format for new account");
        }
        this.emailAddress = emailAddress;
        return this;
    }

    public AccountRegistrationRequest withPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new InvalidRequestException("Password required for new account");
        }
        this.password = password;
        return this;
    }

    public AccountRegistrationRequest confirmPassword(String password) {
        if (password == null || password.isEmpty() || !password.equals(this.password)) {
            throw new InvalidRequestException("Passwords do not match for new account");
        }
        return this;
    }
}
