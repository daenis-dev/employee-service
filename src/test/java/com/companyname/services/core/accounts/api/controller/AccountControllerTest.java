package com.companyname.services.core.accounts.api.controller;

import com.companyname.services.core.accounts.api.behavior.Login;
import com.companyname.services.core.accounts.api.behavior.RegisterAccount;
import com.companyname.services.core.accounts.api.behavior.ResetPassword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Mock
    private RegisterAccount registerAccount;

    @Mock
    private Login login;

    @Mock
    private ResetPassword resetPassword;

    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new AccountController(registerAccount, login, resetPassword))
                .build();
    }

    @Test
    void registersAccount() throws Exception {
        mockMvc.perform(post("/v1/accounts")
                        .param("first-name", "Jimmy")
                        .param("last-name", "Recard")
                        .param("email-address", "jimmy.recard@jr.com")
                        .param("password", "changeit")
                        .param("confirmed-password", "changeit"))
                .andExpect(status().isCreated());
    }

    @Test
    void logsIn() throws Exception {
        mockMvc.perform(post("/v1/accounts/login")
                        .param("email-address", "jimmy.recard@jr.com")
                        .param("password", "changeit"))
                .andExpect(status().isOk());
    }

    @Test
    void resetsPassword() throws Exception {
        mockMvc.perform(post("/v1/accounts/reset-password")
                        .param("email-address", "jimmy.recard@jr.com"))
                .andExpect(status().isOk());
    }
}