package com.companyname.services.core.accounts.api.behavior;

import com.companyname.services.core.accounts.api.model.ResetPasswordRequest;

public interface ResetPassword {

    void sendLinkToResetPassword(ResetPasswordRequest request);
}
