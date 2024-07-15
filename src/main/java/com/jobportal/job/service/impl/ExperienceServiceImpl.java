package com.jobportal.job.service.impl;

import com.jobportal.job.dtos.ExperienceDto;
import com.jobportal.job.enums.LocationType;
import com.jobportal.job.loggers.MainLogger;
import com.jobportal.job.loggers.messages.ExperienceMessage;
import com.jobportal.job.model.Experience;
import com.jobportal.job.repository.ExperienceRepository;
import com.jobportal.job.repository.ProfileRepository;
import com.jobportal.job.service.ExperienceService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.List;

import static java.lang.System.currentTimeMillis;

public class ExperienceServiceImpl implements ExperienceService {
    private final ProfileRepository profileRepository;
    private final ExperienceRepository experienceRepository;
    private final static MainLogger LOGGER = new MainLogger(ExperienceServiceImpl.class);

    public ExperienceServiceImpl(ProfileRepository profileRepository, ExperienceRepository experienceRepository) {
        this.profileRepository = profileRepository;
        this.experienceRepository = experienceRepository;
    }

    @Override
    @Transactional
    public String createExperience(ExperienceDto experienceDto) {
        if (experienceDto.getStartDate().after(experienceDto.getEndDate())) {
            LOGGER.log(ExperienceMessage.END_DATE_BEFORE_START_DATE, HttpStatus.BAD_REQUEST);
        }
        Experience experience = convertToEntity(experienceDto);
        experience.setProfile(profileRepository.findById(experienceDto.getProfileId())
                .orElseThrow(() -> {
                    LOGGER.log(ExperienceMessage.PROFILE_NOT_FOUND + experienceDto.getProfileId(), HttpStatus.NOT_FOUND);
                    return null;
                }));
        experienceRepository.save(experience);
        return ExperienceMessage.EXPERIENCE_CREATED_SUCCESS;
    }

    @Override
    @Transactional
    public String deleteExperience(Long id) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.log(ExperienceMessage.EXPERIENCE_NOT_FOUND + id, HttpStatus.NOT_FOUND);
                    return null;
                });

        experienceRepository.deleteById(id);

        LOGGER.log(String.format(ExperienceMessage.EXPERIENCE_DELETED_LOG, id));
        return ExperienceMessage.EXPERIENCE_DELETED_SUCCESS;
    }

    @Override
    @Transactional
    public String updateExperience(ExperienceDto experienceDto) {
        Experience existingExperience = experienceRepository.findById(experienceDto.getId())
                .orElseThrow(() -> {
                    LOGGER.log(ExperienceMessage.EXPERIENCE_NOT_FOUND + experienceDto.getId(), HttpStatus.NOT_FOUND);
                    return null;
                });

        existingExperience.setCompanyName(experienceDto.getCompanyName());
        existingExperience.setActive(experienceDto.isActive());
        existingExperience.setStartDate(new Timestamp(System.currentTimeMillis()));
        existingExperience.setEndDate(new Timestamp(System.currentTimeMillis()));
        existingExperience.setLocation(experienceDto.getLocation());
        existingExperience.setLocationType(stringToLocationType(experienceDto.getLocationType()));
        existingExperience.setPosition(experienceDto.getPosition());
        existingExperience.setDescription(experienceDto.getDescription());

        experienceRepository.save(existingExperience);
        return ExperienceMessage.EXPERIENCE_UPDATED_SUCCESS;
    }

    @Override
    public List<ExperienceDto> getAllExperiences() {
        return null;
    }

    @Override
    public ExperienceDto getExperienceById(Long id) {
        return null;
    }

    private ExperienceDto convertToDto(Experience experience) {

        if (experience == null) {
            return null;
        }

        Timestamp currentTimestamp = new Timestamp(currentTimeMillis());
        return ExperienceDto.builder()
                .companyName(experience.getCompanyName())
                .isActive(experience.isActive())
                .startDate(experience.getStartDate() != null ? new Timestamp(experience.getStartDate().getTime()) : currentTimestamp)
                .endDate(experience.getEndDate() != null ? new Timestamp(experience.getStartDate().getTime()) : currentTimestamp)
                .location(experience.getLocation())
                .locationType(locationTypeToString(experience.getLocationType()))
                .position(experience.getPosition())
                .description(experience.getDescription())
                .profileId(experience.getProfile().getId())
                .build();
    }

    private Experience convertToEntity(ExperienceDto experienceDto) {

        if (experienceDto == null) {
            return null;
        }

        Timestamp currentTimestamp = new Timestamp(currentTimeMillis());
        return Experience.builder()
                .companyName(experienceDto.getCompanyName())
                .isActive(experienceDto.isActive())
                .startDate(experienceDto.getStartDate() != null ? new Timestamp(experienceDto.getStartDate().getTime()) : currentTimestamp)
                .endDate(experienceDto.getEndDate() != null ? new Timestamp(experienceDto.getEndDate().getTime()) : currentTimestamp)
                .location(experienceDto.getLocation())
                .locationType(stringToLocationType(experienceDto.getLocationType()))
                .position(experienceDto.getPosition())
                .description(experienceDto.getDescription())
                .build();
    }
    @Override
    public LocationType stringToLocationType(String locationType) {
        return LocationType.valueOf(locationType);
    }

    @Override
    public String locationTypeToString(LocationType locationType) {
        return locationType.getDescription();
    }

}
