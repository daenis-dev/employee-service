package com.companyname.services.core.accounts.api.behavior;

import com.companyname.services.core.accounts.api.model.LoginRequest;
import com.companyname.services.core.accounts.api.model.LoginResponse;

public interface Login {

    LoginResponse forRequest(LoginRequest request);
}
