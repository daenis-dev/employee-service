package com.companyname.services.core.accounts;

import com.companyname.services.core.accounts.api.model.LoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KeycloakTokenTest {

    private KeycloakToken keycloakToken;

    @BeforeEach
    void init() {
        keycloakToken = new KeycloakToken();
    }

    @Test
    void mapsToLoginResponse() {
        String accessToken = "xyz";
        keycloakToken.setAccessToken(accessToken);

        LoginResponse theResponse = keycloakToken.mapToLoginResponse();

        assertThat(theResponse.getAccessToken()).isEqualTo("Bearer xyz");
    }

    @Test
    void doesNotMapToLoginResponseForNullToken() {
        keycloakToken.setAccessToken(null);

        RuntimeException theException = assertThrows(RuntimeException.class, () -> keycloakToken.mapToLoginResponse());

        assertThat(theException.getMessage()).isEqualTo("Error occurred while logging into authorization server");
    }

    @Test
    void doesNotMapToLoginResponseForEmptyToken() {
        keycloakToken.setAccessToken("");

        RuntimeException theException = assertThrows(RuntimeException.class, () -> keycloakToken.mapToLoginResponse());

        assertThat(theException.getMessage()).isEqualTo("Error occurred while logging into authorization server");
    }
}