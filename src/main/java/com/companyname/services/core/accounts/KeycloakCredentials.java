package com.companyname.services.core.accounts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class KeycloakCredentials {

    private String value;
    private boolean temporary;
}
