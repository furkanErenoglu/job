package com.jobportal.job.controllers;

import com.jobportal.job.dtos.ProfileDto;
import com.jobportal.job.service.ProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public String createProfile(@RequestBody ProfileDto profileDto) {
        return profileService.createProfile(profileDto);
    }

    @PutMapping
    public String updateProfile(@RequestBody ProfileDto profileDto) {
        return profileService.updateProfile(profileDto);
    }

    @DeleteMapping("/{id}")
    public String deleteProfile(@PathVariable Long id) {
        return profileService.deleteProfile(id);
    }

    @GetMapping("/{id}")
    public ProfileDto getProfileById(@PathVariable Long id) {
        return profileService.getProfileById(id);
    }

    @GetMapping
    public List<ProfileDto> getAllProfiles() {
        return profileService.getAllProfiles();
    }

}
