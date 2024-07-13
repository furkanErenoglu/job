package com.jobportal.job.service.impl;

import com.jobportal.job.dtos.ProjectDto;
import com.jobportal.job.loggers.MainLogger;
import com.jobportal.job.model.Project;
import com.jobportal.job.repository.ProjectRepository;
import com.jobportal.job.service.ProfileService;
import com.jobportal.job.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final static MainLogger LOGGER = new MainLogger(ProjectServiceImpl.class);
    private final ProfileService profileService;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProfileService profileService) {
        this.projectRepository = projectRepository;
        this.profileService = profileService;
    }

    @Override
    public String createProject(ProjectDto project) {
        Project projectEntity = convertToEntity(project);
        projectEntity.setProfile(profileService.getProfileEntityById(project.getProfileId()));
        projectRepository.save(projectEntity);
        return null;
    }

    @Override
    public String deleteProject(Long id) {
        return null;
    }

    @Override
    public String updateProject(ProjectDto project) {
        return null;
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        return null;
    }

    @Override
    public ProjectDto getProjectById(Long id) {
        return null;
    }

    @Override
    public Project getProjectEntityById(Long id) {
        return null;
    }

    private ProjectDto convertToDto(Project project) {
        return ProjectDto.builder()
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .position(project.getPosition())
                .url(project.getUrl())
                .technologies(Collections.singletonList(project.getTechnologies()))
                .description(project.getDescription())
                .build();
    }

    private Project convertToEntity(ProjectDto projectDto) {
        return Project.builder()
                .startDate(projectDto.getStartDate())
                .endDate(projectDto.getEndDate())
                .position(projectDto.getPosition())
                .url(projectDto.getUrl())
                .technologies(projectDto.getTechnologies().get(0))
                .description(projectDto.getDescription())
                .build();
    }
}
