package com.jobportal.job.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDto {
    private Long id;
    private String companyName;
    private boolean isActive;
    private Timestamp startDate;
    private Timestamp endDate;
    private String location;
    private String locationType;
    private String position;
    private String description;
    private List<SkillDto> usedSkills;
    private Long profileId;
}
