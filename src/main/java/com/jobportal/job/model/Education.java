package com.jobportal.job.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
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
    private Timestamp graduationDate;
    @Column(name = "start_date")
    private Timestamp startDate;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;
}
