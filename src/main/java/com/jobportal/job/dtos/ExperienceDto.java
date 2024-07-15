package com.jobportal.job.dtos;

import java.sql.Timestamp;
import java.util.List;

public class ExperienceDto {
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
