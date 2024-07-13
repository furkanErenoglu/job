package com.jobportal.job.service.impl;

import com.jobportal.job.dtos.ProfileDto;
import com.jobportal.job.loggers.MainLogger;
import com.jobportal.job.loggers.messages.ProfilesMessage;
import com.jobportal.job.loggers.messages.UserMessage;
import com.jobportal.job.model.Profile;
import com.jobportal.job.repository.ProfileRepository;
import com.jobportal.job.repository.UserRepository;
import com.jobportal.job.service.ProfileService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final static MainLogger LOGGER = new MainLogger(ProfileServiceImpl.class);

    public ProfileServiceImpl(ProfileRepository profileRepository,UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public String createProfile(ProfileDto profileDto) {
        Profile profileEntity = toEntity(profileDto);
        profileEntity.setUser(userRepository.findById(profileDto.getUserId()).orElseThrow(
                        () -> {
                            LOGGER.log(UserMessage.USER_NOT_FOUND + profileDto.getUserId(), HttpStatus.NOT_FOUND);
                            return null;
                        }
                )
        );

        Profile savedProfile = profileRepository.save(profileEntity);

        return ProfilesMessage.PROFILE_CREATED + savedProfile.getId();
    }

    @Override
    @Transactional
    public String deleteProfile(Long id) {
        profileRepository.deleteById(id);
        return ProfilesMessage.PROFILE_DELETED + id;
    }

    @Override
    @Transactional
    public String updateProfile(ProfileDto profile) {
        Profile existingProfile = profileRepository.findById(profile.getId())
                .orElseThrow(
                        () -> {
                            LOGGER.log(ProfilesMessage.PROFILE_NOT_FOUND + profile.getId(), null);
                            return null;
                        }
                );
        existingProfile.setSector(profile.getSector());
        existingProfile.setBirthDate(profile.getBirthDate());
        existingProfile.setSummary(profile.getSummary());
        profileRepository.save(existingProfile);
        return ProfilesMessage.PROFILE_UPDATED + profile.getId();
    }

    @Override
    public List<ProfileDto> getAllProfiles() {
        return profileRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ProfileDto getProfileById(Long id) {
        Profile profile = profileRepository.findById(id).orElseThrow(
                () -> {
                    LOGGER.log(ProfilesMessage.PROFILE_NOT_FOUND + id, HttpStatus.NOT_FOUND);
                    return null;
                }
        );
        return toDto(profile);
    }

    public Profile getProfileEntityById(Long id) {
        return profileRepository.findById(id).orElseThrow(
                () -> {
                    LOGGER.log(ProfilesMessage.PROFILE_NOT_FOUND + id, HttpStatus.NOT_FOUND);
                    return null;
                }
        );
    }
    private Profile toEntity(ProfileDto profileDto) {
        return Profile.builder()
                .sector(profileDto.getSector())
                .birthDate(new Timestamp(System.currentTimeMillis()))
                .summary(profileDto.getSummary())
                .build();
    }

    private ProfileDto toDto(Profile profile) {
        return ProfileDto.builder()
                .sector(profile.getSector())
                .birthDate(profile.getBirthDate())
                .summary(profile.getSummary())
                .build();
    }

}
