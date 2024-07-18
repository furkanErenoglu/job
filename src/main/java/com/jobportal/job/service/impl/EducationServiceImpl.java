package com.jobportal.job.service.impl;

import com.jobportal.job.dtos.EducationDto;
import com.jobportal.job.loggers.MainLogger;
import com.jobportal.job.loggers.messages.EducationMessage;
import com.jobportal.job.model.Education;
import com.jobportal.job.model.Profile;
import com.jobportal.job.repository.EducationRepository;
import com.jobportal.job.service.EducationService;
import com.jobportal.job.service.ProfileService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EducationServiceImpl implements EducationService {
    private final EducationRepository educationRepository;
    private final ProfileService profileService;
    private final static MainLogger LOGGER = new MainLogger(EducationServiceImpl.class);

    public EducationServiceImpl(EducationRepository educationRepository, ProfileService profileService) {
        this.educationRepository = educationRepository;
        this.profileService = profileService;
    }

    @Override
    public String createEducation(EducationDto educationDto) {
        if (educationDto.getStartDate().after(educationDto.getGraduationDate())) {
            LOGGER.log(EducationMessage.GRADUATION_DATE_BEFORE_START_DATE, HttpStatus.BAD_REQUEST);
            return null;
        }
        Education education = toEntity(educationDto);
        education.setProfile(profileService.getProfileEntityById(educationDto.getProfileId()));
        educationRepository.save(education);
        return EducationMessage.EDUCATION_CREATED_SUCCESS;
    }

    @Override
    public String deleteEducation(Long id) {
        return null;
    }

    @Override
    public String updateEducation(long educationId, EducationDto educationDto) {
        return null;
    }

    @Override
    public Page<EducationDto> getAllEducations(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public EducationDto getEducationsById(Long id) {
        return null;
    }

    private Education toEntity(EducationDto educationDto) {
        if (educationDto == null) {
            return null;
        }

        Profile profile = Profile.builder().id(educationDto.getProfileId()).build();

        return Education.builder()
                .university(educationDto.getUniversity())
                .department(educationDto.getDepartment())
                .graduationDate(educationDto.getGraduationDate())
                .description(educationDto.getDescription())
                .startDate(educationDto.getStartDate())
                .profile(profile)
                .build();
    }

    private EducationDto toDto(Education education) {
        if (education == null) {
            return null;
        }

        return EducationDto.builder()
                .university(education.getUniversity())
                .department(education.getDepartment())
                .graduationDate(education.getGraduationDate())
                .description(education.getDescription())
                .startDate(education.getStartDate())
                .profileId(education.getProfile().getId())
                .build();
    }
}
