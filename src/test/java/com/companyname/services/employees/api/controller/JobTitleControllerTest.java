package com.companyname.services.employees.api.controller;

import com.companyname.services.employees.api.behavior.FindAllJobTitles;
import com.companyname.services.employees.api.model.JobTitleDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class JobTitleControllerTest {

    @Mock
    private FindAllJobTitles findAllJobTitles;

    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new JobTitleController(findAllJobTitles))
                .build();
    }

    @Test
    void findsAllJobTitles() throws Exception {
        String jobTitle = "Sales";
        List<JobTitleDetails> jobTitles = Collections.singletonList(new JobTitleDetails(1, jobTitle));

        when(findAllJobTitles.findAllJobTitles()).thenReturn(jobTitles);

        mockMvc.perform(get("/v1/job-titles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(jobTitle)));
    }
}