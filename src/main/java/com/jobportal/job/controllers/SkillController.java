package com.jobportal.job.controllers;

import com.jobportal.job.dtos.SkillDto;
import com.jobportal.job.service.SkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill")
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public String createSkill(@RequestBody SkillDto skillDto) {
        return skillService.createSkill(skillDto);
    }

    @DeleteMapping
    public String deleteSkill(@RequestParam Long id) {
        return skillService.deleteSkill(id);
    }

    @PutMapping
    public String updateSkill(@RequestBody SkillDto skillDto) {
        return skillService.updateSkill(skillDto);
    }

    @GetMapping
    public SkillDto getSkill(@RequestParam Long id) {
        return skillService.getSkill(id);
    }

}
