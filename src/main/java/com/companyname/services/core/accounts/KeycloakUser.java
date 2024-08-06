package com.companyname.services.core.accounts;

import com.companyname.services.core.accounts.api.model.AccountRegistrationRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.*;

@Getter
@EqualsAndHashCode
public class KeycloakUser {

    private String firstName;
    private String lastName;
    private String email;
    private boolean emailVerified;
    private boolean enabled;
    private String username;
    private List<KeycloakCredentials> credentials;

    KeycloakUser mappedFrom(AccountRegistrationRequest request) {
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.email = request.getEmailAddress();
        this.emailVerified = true;
        this.enabled = true;
        this.username = request.getEmailAddress();
        this.credentials = Collections.singletonList(new KeycloakCredentials(request.getPassword(), false));
        return this;
    }
}
