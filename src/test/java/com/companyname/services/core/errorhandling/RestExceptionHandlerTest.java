package com.companyname.services.core.errorhandling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RestExceptionHandlerTest {

    private RestExceptionHandler restExceptionHandler;

    @BeforeEach
    void init() {
        restExceptionHandler = new RestExceptionHandler();
    }

    @Test
    void handlesException() {
        Exception exception = new Exception("Error occurred");

        ResponseEntity<ApiError> theResponse = restExceptionHandler.handle(exception);

        assertThat(theResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(theResponse.getBody().getMessage()).isEqualTo("Error occurred");
    }

    @Test
    void handlesInvalidRequest() {
        InvalidRequestException exception = new InvalidRequestException("Error occurred");

        ResponseEntity<ApiError> theResponse = restExceptionHandler.handleInvalidRequest(exception);

        assertThat(theResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(theResponse.getBody().getMessage()).isEqualTo("Error occurred");
    }
}