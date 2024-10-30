package com.companyname.services.employees;

import com.companyname.services.employees.api.model.CompanyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByName(String name);

    @Query("SELECT NEW com.companyname.services.employees.api.model.CompanyDetails(c.id, c.name) FROM Company c")
    List<CompanyDetails> findAllCompanyDetails();
}
