package com.jobportal.job.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationDto {
    private String university;
    private String department;
    private Timestamp graduationDate;
    private String description;
    private Timestamp startDate;
    private Long profileId;
}
