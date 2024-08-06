package com.companyname.services.core.accounts.api.behavior;

import com.companyname.services.core.accounts.api.model.AccountRegistrationRequest;

public interface RegisterAccount {

    void forRequest(AccountRegistrationRequest request);
}
