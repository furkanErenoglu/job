package com.jobportal.job.service;

import com.jobportal.job.dtos.UserDto;

import java.util.List;

public interface UserService {
    String createUser(UserDto userDto);
    String updateUser(UserDto userDto);
    String deleteUser(String uuid);
    UserDto getUserByUuid(String uuid);
    List<UserDto> getAllUsers();
}
