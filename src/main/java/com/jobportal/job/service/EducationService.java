package com.jobportal.job.service;

public interface EducationService {
    String createEducation(EducationDto educationDto);

    String deleteEducation(Long id);

    String updateEducation(long educationId, EducationDto educationDto);

    Page<EducationDto> getAllEducations(int pageNo, int pageSize);

    EducationDto getEducationsById(Long id);
}
