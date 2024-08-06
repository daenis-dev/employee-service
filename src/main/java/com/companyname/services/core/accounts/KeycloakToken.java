package com.companyname.services.core.accounts;

import com.companyname.services.core.accounts.api.model.LoginResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
final class KeycloakToken {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonIgnore
    LoginResponse mapToLoginResponse() {
        if (accessToken == null || accessToken.isEmpty()) {
            throw new RuntimeException("Error occurred while logging into authorization server");
        }
        return new LoginResponse("Bearer ".concat(accessToken));
    }
}
