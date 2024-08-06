package com.companyname.services.core.accounts;

import com.companyname.services.core.accounts.api.model.AccountRegistrationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KeycloakUserTest {

    private KeycloakUser keycloakUser;

    @BeforeEach
    void init() {
        keycloakUser = new KeycloakUser();
    }

    @Test
    void mapsFromUserRegistrationRequest() {
        String theFirstName = "Jimmy";
        String theLastName = "Recard";
        String theEmailAddress = "jimmy.recard@jr.com";
        String thePassword = "changeit";

        KeycloakUser theUpdatedUser = keycloakUser.mappedFrom(new AccountRegistrationRequest()
                .withFirstName(theFirstName)
                .withLastName(theLastName)
                .withEmailAddress(theEmailAddress)
                .withPassword(thePassword));

        assertThat(theUpdatedUser.getFirstName()).isEqualTo(theFirstName);
        assertThat(theUpdatedUser.getLastName()).isEqualTo(theLastName);
        assertThat(theUpdatedUser.getEmail()).isEqualTo(theEmailAddress);
        assertThat(theUpdatedUser.isEmailVerified()).isTrue();
        assertThat(theUpdatedUser.isEnabled()).isTrue();
        assertThat(theUpdatedUser.getUsername()).isEqualTo(theEmailAddress);
        assertThat(theUpdatedUser.getCredentials().get(0).getValue()).isEqualTo(thePassword);
        assertThat(theUpdatedUser.getCredentials().get(0).isTemporary()).isFalse();
    }
}