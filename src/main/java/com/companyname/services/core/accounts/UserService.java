package com.companyname.services.core.accounts;

import com.companyname.services.core.accounts.api.behavior.Login;
import com.companyname.services.core.accounts.api.behavior.RegisterAccount;
import com.companyname.services.core.accounts.api.model.AccountRegistrationRequest;
import com.companyname.services.core.accounts.api.model.LoginRequest;
import com.companyname.services.core.accounts.api.model.LoginResponse;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class UserService implements RegisterAccount, Login {

    @Value("${keycloak.base-url}")
    private String KEYCLOAK_BASE_URL;

    @Value("${keycloak.admin.realm}")
    private String KEYCLOAK_ADMIN_REALM;

    @Value("${keycloak.admin.username}")
    private String KEYCLOAK_ADMIN_USERNAME;

    @Value("${keycloak.admin.password}")
    private String KEYCLOAK_ADMIN_PASSWORD;

    @Value("${keycloak.admin.client-name}")
    private String KEYCLOAK_ADMIN_CLIENT_NAME;

    @Value("${keycloak.employee-management-service.base-url}")
    private String KEYCLOAK_EMPLOYEE_MANAGEMENT_SERVICE_BASE_URL;

    @Value("${keycloak.employee-management-service.users-api-url}")
    private String KEYCLOAK_EMPLOYEE_MANAGEMENT_SERVICE_USERS_API;

    @Value("${keycloak.employee-management-service.token-schema}")
    private String KEYCLOAK_EMPLOYEE_MANAGEMENT_SERVICE_TOKEN_SCHEMA;

    @Value("${keycloak.employee-management-service.login-url}")
    private String KEYCLOAK_EMPLOYEE_MANAGEMENT_SERVICE_LOGIN_URL;

    @Override
    public void forRequest(AccountRegistrationRequest request) {
        sendARegistrationRequestToAuthorizationServerForThe(request);
    }

    private void sendARegistrationRequestToAuthorizationServerForThe(AccountRegistrationRequest request) {
        Keycloak keycloak = Keycloak.getInstance(KEYCLOAK_BASE_URL, KEYCLOAK_ADMIN_REALM, KEYCLOAK_ADMIN_USERNAME, KEYCLOAK_ADMIN_PASSWORD, KEYCLOAK_ADMIN_CLIENT_NAME);
        String accessToken = KEYCLOAK_EMPLOYEE_MANAGEMENT_SERVICE_TOKEN_SCHEMA + " " + keycloak.tokenManager().getAccessTokenString();

        WebClient webClient = WebClient
                .builder()
                .baseUrl(KEYCLOAK_EMPLOYEE_MANAGEMENT_SERVICE_BASE_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add(HttpHeaders.ACCEPT, ALL_VALUE);
                    httpHeaders.add(HttpHeaders.AUTHORIZATION, accessToken);
                })
                .build();

        try {
            KeycloakResponse keycloakResponse = webClient
                    .post()
                    .uri(KEYCLOAK_EMPLOYEE_MANAGEMENT_SERVICE_USERS_API)
                    .contentType(APPLICATION_JSON)
                    .bodyValue(new KeycloakUser().mappedFrom(request))
                    .exchangeToMono(response -> response.bodyToMono(KeycloakResponse.class))
                    .block(Duration.ofSeconds(10));
            if (keycloakResponse != null && keycloakResponse.getError() != null && !keycloakResponse.getError().isEmpty()) {
                throw new RuntimeException("An error occurred while registering the user with message from Keycloak: " + keycloakResponse.getError());
            }
        } catch (Exception ex) {
            throw new RuntimeException("An error occurred while registering the user", ex);
        }
    }

    @Override
    public LoginResponse forRequest(LoginRequest request) {
        return theAccessTokenFromTheAuthorizationServerForThe(request);
    }

    private LoginResponse theAccessTokenFromTheAuthorizationServerForThe(LoginRequest request) {
        WebClient webClient = WebClient
                .builder()
                .baseUrl(KEYCLOAK_EMPLOYEE_MANAGEMENT_SERVICE_LOGIN_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add(HttpHeaders.ACCEPT, ALL_VALUE);
                })
                .build();
        return Optional.of(Objects.requireNonNull(
                webClient
                        .post()
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .body(BodyInserters
                                .fromFormData("username", request.getEmailAddress())
                                .with("password", request.getPassword())
                                .with("client_id", "employee-service")
                                .with("grant_type", "password"))
                        .exchangeToMono(response -> response.bodyToMono(KeycloakToken.class))
                        .block(Duration.ofSeconds(10))))
                .orElseThrow(() -> new RuntimeException("Error occurred while logging into authorization server"))
                .mapToLoginResponse();
    }
}
