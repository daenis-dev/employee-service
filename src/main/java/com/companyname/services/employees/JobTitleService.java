package com.companyname.services.employees;

import com.companyname.services.employees.api.behavior.FindAllJobTitles;
import com.companyname.services.employees.api.model.JobTitleDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class JobTitleService implements FindAllJobTitles {

    private final JobTitleRepository jobTitleRepository;

    @Override
    public List<JobTitleDetails> findAllJobTitles() {
        return jobTitleRepository.findAllJobTitleDetails();
    }
}
