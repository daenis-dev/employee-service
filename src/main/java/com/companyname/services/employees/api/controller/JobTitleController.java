package com.companyname.services.employees.api.controller;

import com.companyname.services.employees.api.behavior.FindAllJobTitles;
import com.companyname.services.employees.api.model.JobTitleDetails;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// TODO: test
@RestController
@AllArgsConstructor
public class JobTitleController {

    private final FindAllJobTitles findAllJobTitles;

    @GetMapping("/v1/job-titles")
    public ResponseEntity<List<JobTitleDetails>> findAllJobTitles() {
        return ResponseEntity.ok(findAllJobTitles.findAllJobTitles());
    }
}
