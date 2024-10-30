package com.companyname.services.employees.api.model;

import com.companyname.services.core.errorhandling.InvalidRequestException;
import lombok.Getter;

// TODO: test
@Getter
public final class DeleteEmployeeRequest {

    private long id;

    public DeleteEmployeeRequest withId(String id) {
        if (id == null || id.isEmpty()) {
            throw new InvalidRequestException("ID required to delete employee");
        }
        boolean theIdContainsNonNumericCharacters = !id.matches("\\d*");
        if (theIdContainsNonNumericCharacters) {
            throw new InvalidRequestException("ID value must be numeric to delete employee");
        }
        this.id = Long.parseLong(id);
        return this;
    }
}
