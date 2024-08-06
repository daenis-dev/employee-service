package com.companyname.services.core.accounts.api.controller;

import com.companyname.services.core.accounts.api.behavior.Login;
import com.companyname.services.core.accounts.api.behavior.RegisterAccount;
import com.companyname.services.core.accounts.api.model.AccountRegistrationRequest;
import com.companyname.services.core.accounts.api.model.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final RegisterAccount registerAccount;
    private final Login login;

    @PostMapping("/v1/accounts")
    public ResponseEntity<?> registerAccount(@RequestParam("first-name") String firstName, @RequestParam("last-name") String lastName, @RequestParam("email-address") String emailAddress, @RequestParam("password") String password, @RequestParam("confirmed-password") String confirmedPassword) {
        registerAccount.forRequest(new AccountRegistrationRequest()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmailAddress(emailAddress)
                .withPassword(password)
                .confirmPassword(confirmedPassword));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/v1/accounts/login")
    public ResponseEntity<?> login(@RequestParam("email-address") String emailAddress, @RequestParam("password") String password) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(login.forRequest(new LoginRequest()
                        .withEmailAddress(emailAddress)
                        .withPassword(password)));
    }
}
