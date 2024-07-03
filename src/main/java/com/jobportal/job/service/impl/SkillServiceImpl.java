package com.jobportal.job.service.impl;

import com.jobportal.job.dtos.SkillDto;
import com.jobportal.job.model.Skills;
import com.jobportal.job.service.SkillService;

public class SkillServiceImpl implements SkillService {
    @Override
    public String createSkill(SkillDto skillDto) {
        return null;
    }

    @Override
    public String updateSkill(SkillDto skillDto) {
        return null;
    }

    @Override
    public String deleteSkill(long skillId) {
        return null;
    }

    @Override
    public SkillDto getSkill(long skillId) {
        return null;
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
