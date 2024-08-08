package com.jobportal.job.service.impl;

import com.jobportal.job.dtos.EducationDto;
import com.jobportal.job.loggers.MainLogger;
import com.jobportal.job.loggers.messages.EducationMessage;
import com.jobportal.job.model.Education;
import com.jobportal.job.model.Profile;
import com.jobportal.job.repository.EducationRepository;
import com.jobportal.job.service.EducationService;
import com.jobportal.job.service.ProfileService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional
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
    @Transactional
    public String deleteEducation(Long id) {
        if (!educationRepository.existsById(id)) {
            LOGGER.log(EducationMessage.EDUCATION_NOT_FOUND + id, HttpStatus.NOT_FOUND);
            return null;
        }
        educationRepository.deleteById(id);
        return EducationMessage.EDUCATION_DELETED_SUCCESS;
    }

    @Override
    @Transactional
    public String updateEducation(long educationId, EducationDto educationDto) {
        Education existingEducation = educationRepository.findById(educationId)
                .orElseThrow(() -> {
                    LOGGER.log(EducationMessage.EDUCATION_NOT_FOUND + educationId, HttpStatus.NOT_FOUND);
                    return null;
                });

        existingEducation.setUniversity(educationDto.getUniversity());
        existingEducation.setDepartment(educationDto.getDepartment());
        existingEducation.setGraduationDate(educationDto.getGraduationDate());
        existingEducation.setDescription(educationDto.getDescription());
        existingEducation.setStartDate(educationDto.getStartDate());

        educationRepository.save(existingEducation);
        return EducationMessage.EDUCATION_UPDATED_SUCCESS;
    }

    @Override
    public Page<EducationDto> getAllEducations(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Education> educations = educationRepository.findAll(pageable);
        List<EducationDto> educationDtoList = educations.getContent().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(educationDtoList, pageable, educations.getTotalElements());
    }

    @Override
    public EducationDto getEducationsById(Long id) {
        Education education = educationRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.log(EducationMessage.EDUCATION_NOT_FOUND + id, HttpStatus.NOT_FOUND);
                    return null;
                });
        return toDto(education);
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


    public Education toEntity(EducationDto educationDto) {
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

}
