package com.jobportal.job.service;

import com.jobportal.job.dtos.ExperienceDto;
import com.jobportal.job.enums.LocationType;

import java.util.List;

public interface ExperienceService {
    String createExperience(ExperienceDto experience);
    String deleteExperience(Long id);
    String updateExperience(ExperienceDto experience);
    List<ExperienceDto> getAllExperiences();
    ExperienceDto getExperienceById(Long id);
    String locationTypeToString(LocationType locationType);
    LocationType stringToLocationType(String locationType);
}
