package com.jobportal.job.service.impl;

import com.jobportal.job.dtos.ProfileDto;
import com.jobportal.job.dtos.UserDto;
import com.jobportal.job.loggers.MainLogger;
import com.jobportal.job.loggers.messages.UserMessage;
import com.jobportal.job.model.User;
import com.jobportal.job.repository.UserRepository;
import com.jobportal.job.service.ProfileService;
import com.jobportal.job.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserServiceİmpl implements UserService {
    private final UserRepository userRepository;
    private final static MainLogger LOGGER = new MainLogger(UserServiceİmpl.class);
    private final ProfileService profileService;
    public UserServiceİmpl(UserRepository userRepository, ProfileService profileService) {
        this.userRepository = userRepository;
        this.profileService = profileService;
    }
    @Override
    public String createUser(UserDto userDto) {
        User user = convertToEntity(userDto);
        user.setProfile(profileService.getProfileEntityById(userDto.getProfile().getId()));
        userRepository.save(user);
        return UserMessage.USER_CREATED_SUCCESS;
    }

    @Override
    public String updateUser(UserDto userDto) {
        User user = checkToUser(userDto.getUuid());
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setTelephone(userDto.getTelephone());
        user.setAddress(userDto.getAddress());

        userRepository.save(user);
        return UserMessage.USER_UPDATED_SUCCESS;
    }

    @Override
    public String deleteUser(String uuid) {
        User user = checkToUser(uuid);
        userRepository.delete(user);
        return UserMessage.USER_DELETED_SUCCESS;
    }

    @Override
    public UserDto getUserByUuid(String uuid) {
        User user = checkToUser(uuid);
        return convertToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    private UserDto convertToDto(User user) {
        return UserDto.builder()
                .uuid(user.getUuid())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .telephone(user.getTelephone())
                .address(user.getAddress())
                .profile(profileService.getProfileById(user.getProfile().getId()))
                .build();
    }

    private User convertToEntity(UserDto userDto) {
        return User.builder()
                .uuid(userDto.getUuid())
                .fullName(userDto.getFullName())
                .email(userDto.getEmail())
                .telephone(userDto.getTelephone())
                .address(userDto.getAddress())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    private User checkToUser(String uuid) {
        return userRepository.findByUuid(uuid)
                .orElseThrow(() -> {
                    LOGGER.log(UserMessage.USER_NOT_FOUND + uuid, HttpStatus.NOT_FOUND);
                    return null;
                });
    }
}

