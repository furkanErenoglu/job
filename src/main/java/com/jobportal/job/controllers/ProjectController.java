package com.jobportal.job.controllers;

import com.jobportal.job.dtos.ProjectDto;
import com.jobportal.job.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public String createProject(@RequestBody ProjectDto projectDto) {
        return projectService.createProject(projectDto);
    }

    @DeleteMapping
    public String deleteProject(@RequestParam Long id) {
        return projectService.deleteProject(id);
    }

    @PutMapping
    public String updateProject(@RequestBody ProjectDto projectDto) {
        return projectService.updateProject(projectDto);
    }

    @GetMapping
    public ProjectDto getProject(@RequestParam Long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("/all")
    public List<ProjectDto> getAllProjects() {
        return projectService.getAllProjects();
    }
}
