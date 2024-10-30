package com.companyname.services.employees;

import com.companyname.services.employees.api.model.JobTitleDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface JobTitleRepository extends JpaRepository<JobTitle, Long> {

    Optional<JobTitle> findByName(String name);

    @Query("SELECT NEW com.companyname.services.employees.api.model.JobTitleDetails(jt.id, jt.name) FROM JobTitle jt")
    List<JobTitleDetails> findAllJobTitleDetails();
}
