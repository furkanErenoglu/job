package com.jobportal.job.service;

import com.jobportal.job.dtos.ProfileDto;

import java.util.List;

public interface ProfileService {
    String createProfile(ProfileDto profile);
    String deleteProfile(Long id);
    String updateProfile(ProfileDto profile);
    List<ProfileDto> getAllProfiles();
    ProfileDto getProfileById(Long id);
}
