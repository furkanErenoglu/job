package com.jobportal.job.service;

import com.jobportal.job.dtos.SkillDto;

public interface SkillService {
    String createSkill(SkillDto skillDto);
    String updateSkill(SkillDto skillDto);
    String deleteSkill(long skillId);
    SkillDto getSkill(long skillId);
}
