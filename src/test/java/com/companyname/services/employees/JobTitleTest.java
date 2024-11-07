package com.companyname.services.employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JobTitleTest {

    private long id;
    private String name;

    private JobTitle jobTitle;

    @BeforeEach
    void init() {
        jobTitle = new JobTitle();
        jobTitle.setId(id);
        jobTitle.setName(name);
    }

    @Test
    void jobTitlesWithEqualPropertiesAreEqual() {
        JobTitle theOtherJobTitle = new JobTitle();
        theOtherJobTitle.setId(id);
        theOtherJobTitle.setName(name);

        boolean jobTitleEqualsTheOtherJobTitle = jobTitle.equals(theOtherJobTitle);

        assertThat(jobTitleEqualsTheOtherJobTitle).isTrue();
    }

    @Test
    void jobTitlesWithoutEqualPropertiesAreNotEqual() {
        JobTitle theOtherJobTitle = new JobTitle();
        theOtherJobTitle.setId(2L);
        theOtherJobTitle.setName(name);

        boolean jobTitleEqualsTheOtherJobTitle = jobTitle.equals(theOtherJobTitle);

        assertThat(jobTitleEqualsTheOtherJobTitle).isFalse();
    }

    @Test
    void getsTheHashCode() {
        int theHashCode = jobTitle.hashCode();

        assertThat(theHashCode).isEqualTo(3524);
    }
}