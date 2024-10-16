package com.jobportal.job.service.impl;

import com.jobportal.job.dtos.SkillDto;
import com.jobportal.job.loggers.MainLogger;
import com.jobportal.job.loggers.messages.SkillMassage;
import com.jobportal.job.model.Skills;
import com.jobportal.job.repository.ProfileRepository;
import com.jobportal.job.repository.SkillsRepository;
import com.jobportal.job.service.SkillService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {
    private final SkillsRepository skillsRepository;
    private final ProfileRepository profileRepository;
    private final static MainLogger LOGGER = new MainLogger(SkillServiceImpl.class);

    public SkillServiceImpl(SkillsRepository skillsRepository, ProfileRepository profileRepository) {
        this.skillsRepository = skillsRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    @Transactional
    public String createSkill(SkillDto skillDto) {
        Skills skills = convertToEntity(skillDto);
        skills.setProfile(profileRepository.findById(Long.parseLong(skillDto.getProfileId())).orElseThrow(
                () -> {
                    LOGGER.log(SkillMassage.SKIILS_NOT_FOUND + skillDto.getProfileId(), null);
                    return null;
                }
        ));
        skillsRepository.save(skills);
        return SkillMassage.SKILL_CREATED + skills.getId();
    }

    @Override
    @Transactional
    public String updateSkill(SkillDto skillDto) {
        Skills existingSkill = skillsRepository.findById(Long.parseLong(skillDto.getProfileId())).get();
        existingSkill.setName(skillDto.getName());
        existingSkill.setDescription(skillDto.getDescription());
        return SkillMassage.SKILL_UPDATED + existingSkill.getId();
    }

    @Override
    @Transactional
    public String deleteSkill(long skillId) {
        skillsRepository.deleteById(skillId);
        return SkillMassage.SKILL_DELETED + skillId;
    }

    @Override
    public SkillDto getSkill(long skillId) {
        return convertToDto(skillsRepository.findById(skillId).get());
    }

    @Override
    public List<SkillDto> searchSkills(String name) {
        return skillsRepository.findByNameContainingIgnoreCase(name).stream().map(this::convertToDto).toList();
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
