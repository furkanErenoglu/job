package com.jobportal.job.service.impl;

import com.jobportal.job.dtos.ExperienceDto;
import com.jobportal.job.loggers.MainLogger;
import com.jobportal.job.repository.ExperienceRepository;
import com.jobportal.job.repository.ProfileRepository;
import com.jobportal.job.service.ExperienceService;

import java.util.List;

public class ExperienceServiceImpl implements ExperienceService {
    private final ProfileRepository profileRepository;
    private final ExperienceRepository experienceRepository;
    private final static MainLogger LOGGER = new MainLogger(ExperienceServiceImpl.class);

    public ExperienceServiceImpl(ProfileRepository profileRepository, ExperienceRepository experienceRepository) {
        this.profileRepository = profileRepository;
        this.experienceRepository = experienceRepository;
    }

    @Override
    public String createExperience(ExperienceDto experience) {
        return null;
    }

    @Override
    public String deleteExperience(Long id) {
        return null;
    }

    @Override
    public String updateExperience(ExperienceDto experience) {
        return null;
    }

    @Override
    public List<ExperienceDto> getAllExperiences() {
        return null;
    }

    @Override
    public ExperienceDto getExperienceById(Long id) {
        return null;
    }
}
