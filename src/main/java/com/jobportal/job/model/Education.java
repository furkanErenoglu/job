package com.jobportal.job.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@Table(name = "educations")
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "university")
    private String university;

    @Column(name = "department")
    private String department;

    @Column(name = "graduation_date")
    private LocalDate graduationDate;

    @Column(name = "description")
    private String description;
}
