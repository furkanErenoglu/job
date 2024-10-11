package com.jobportal.job.controllers;

import com.jobportal.job.dtos.ExperienceDto;
import com.jobportal.job.service.ExperienceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/experience")
public class ExperienceController {
    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @PostMapping
    public String createExperience(@RequestBody ExperienceDto experienceDto) {
        return experienceService.createExperience(experienceDto);
    }

    @DeleteMapping
    public String deleteExperience(@RequestParam Long id) {
        return experienceService.deleteExperience(id);
    }

    @PutMapping
    public String updateExperience(@RequestBody ExperienceDto experienceDto) {
        return experienceService.updateExperience(experienceDto);
    }

    @GetMapping
    public ExperienceDto getExperience(@RequestParam Long id) {
        return experienceService.getExperienceById(id);
    }
}
