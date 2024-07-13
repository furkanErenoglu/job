package com.jobportal.job.service;

import com.jobportal.job.dtos.ProjectDto;
import com.jobportal.job.model.Project;

import java.util.List;

public interface ProjectService {
    String createProject(ProjectDto project);
    String deleteProject(Long id);
    String updateProject(ProjectDto project);
    List<ProjectDto> getAllProjects();
    ProjectDto getProjectById(Long id);
}
