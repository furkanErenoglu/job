package com.jobportal.job.controllers;

import com.jobportal.job.dtos.EducationDto;
import com.jobportal.job.model.Education;
import com.jobportal.job.service.EducationService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/education")
@RestController
public class EducationController {
    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping
    public void addEducation(@RequestBody EducationDto education) {
        educationService.createEducation(education);
    }

    @PutMapping("/{id}")
    public void updateEducation(@PathVariable long id,@RequestBody EducationDto education) {
        educationService.updateEducation(id,education);
    }

    @DeleteMapping("/{id}")
    public void deleteEducation(@PathVariable long id) {
        educationService.deleteEducation(id);
    }

    @GetMapping("/{id}")
    public EducationDto getEducationById(@PathVariable long id) {
        return educationService.getEducationsById(id);
    }

    
}
