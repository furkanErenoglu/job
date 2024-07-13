package com.jobportal.job.service.impl;

import com.jobportal.job.dtos.ProjectDto;
import com.jobportal.job.loggers.MainLogger;
import com.jobportal.job.loggers.messages.ProjectMessage;
import com.jobportal.job.model.Project;
import com.jobportal.job.repository.ProjectRepository;
import com.jobportal.job.service.ProfileService;
import com.jobportal.job.service.ProjectService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional
    public String createProject(ProjectDto project) {
        Project projectEntity = convertToEntity(project);
        projectEntity.setProfile(profileService.getProfileEntityById(project.getProfileId()));
        projectRepository.save(projectEntity);
        return ProjectMessage.PROJECT_CREATED_SUCCESS;
    }

    @Override
    @Transactional
    public String deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.log(ProjectMessage.PROJECT_NOT_FOUND + id, HttpStatus.NOT_FOUND);
                    return null;
                });

        projectRepository.deleteById(id);

        return ProjectMessage.PROJECT_DELETED_SUCCESS;
    }

    @Override
    @Transactional
    public String updateProject(ProjectDto project) {
        Project existingProject = projectRepository.findById(project.getId())
                .orElseThrow(() -> {
                    LOGGER.log(ProjectMessage.PROJECT_NOT_FOUND + project.getId(), HttpStatus.NOT_FOUND);
                    return null;
                });

        existingProject.setStartDate(project.getStartDate());
        existingProject.setEndDate(project.getEndDate());
        existingProject.setPosition(project.getPosition());
        existingProject.setUrl(project.getUrl());
        existingProject.setTechnologies(project.getTechnologies().get(0));
        existingProject.setDescription(project.getDescription());

        projectRepository.save(existingProject);

        return ProjectMessage.PROJECT_UPDATED_SUCCESS;
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.log(ProjectMessage.PROJECT_NOT_FOUND + id, HttpStatus.NOT_FOUND);
                    return null;
                });
        return convertToDto(project);
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
