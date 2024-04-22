package com.jobportal.job.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "description")
    private String description;

    @Column(name = "job_category")
    private String jobCategory;

    @Column(name = "company_information")
    private String companyInformation;

    @Column(name = "owner")
    private String owner;

    @Column(name = "posting_date")
    private LocalDate postingDate;

    @Column(name = "application_deadline")
    private LocalDate applicationDeadline;

}
