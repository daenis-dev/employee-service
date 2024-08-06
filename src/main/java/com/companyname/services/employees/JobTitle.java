package com.companyname.services.employees;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "job_titles")
@Getter(value = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@EqualsAndHashCode
final class JobTitle {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_titles_seq")
    @SequenceGenerator(name = "job_titles_seq", sequenceName = "job_titles_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;
}
