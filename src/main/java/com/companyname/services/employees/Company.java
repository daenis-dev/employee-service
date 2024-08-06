package com.companyname.services.employees;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "companies")
@Getter(value = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@EqualsAndHashCode
final class Company {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companies_seq")
    @SequenceGenerator(name = "companies_seq", sequenceName = "companies_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;
}
