package com.jobportal.job.service;

import com.jobportal.job.dtos.ExperienceDto;
import com.jobportal.job.enums.LocationType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExperienceService {
    String createExperience(ExperienceDto experience);
    String deleteExperience(Long id);
    String updateExperience(ExperienceDto experience);
    Page<ExperienceDto> getAllExperiences(int pageNo, int pageSize);
    ExperienceDto getExperienceById(Long id);
    String locationTypeToString(LocationType locationType);
    LocationType stringToLocationType(String locationType);
}
