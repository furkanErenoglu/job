package com.jobportal.job.service.impl;

import com.jobportal.job.dtos.SkillDto;
import com.jobportal.job.model.Skills;
import com.jobportal.job.repository.ProfileRepository;
import com.jobportal.job.repository.SkillsRepository;
import com.jobportal.job.service.SkillService;
import jakarta.transaction.Transactional;

public class SkillServiceImpl implements SkillService {
    private final SkillsRepository skillsRepository;
    private final ProfileRepository profileRepository;

    public SkillServiceImpl(SkillsRepository skillsRepository, ProfileRepository profileRepository) {
        this.skillsRepository = skillsRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    @Transactional
    public String createSkill(SkillDto skillDto) {
        Skills skills = convertToEntity(skillDto);
        skills.setProfile(profileRepository.findById(Long.parseLong(skillDto.getProfileId())).orElseThrow());
        skillsRepository.save(skills);
        return "Skill created successfully";
    }

    @Override
    @Transactional
    public String updateSkill(SkillDto skillDto) {
        Skills existingSkill = skillsRepository.findById(Long.parseLong(skillDto.getProfileId())).get();
        existingSkill.setName(skillDto.getName());
        existingSkill.setDescription(skillDto.getDescription());
        return "Skill updated successfully";
    }

    @Override
    @Transactional
    public String deleteSkill(long skillId) {
        skillsRepository.deleteById(skillId);
        return "Skill deleted successfully";
    }

    @Override
    public SkillDto getSkill(long skillId) {
        return convertToDto(skillsRepository.findById(skillId).get());
    }

    private SkillDto convertToDto(Skills skills) {
        return SkillDto.builder()
                .name(skills.getName())
                .description(skills.getDescription())
                .profileId(skills.getProfile().getId().toString())
                .build();
    }

    private Skills convertToEntity(SkillDto skillDto) {
        return Skills.builder()
                .name(skillDto.getName())
                .description(skillDto.getDescription())
                .build();
    }
}
