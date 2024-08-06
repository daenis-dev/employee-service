package com.companyname.services.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface JobTitleRepository extends JpaRepository<JobTitle, Long> {

    Optional<JobTitle> findByName(String name);
}
