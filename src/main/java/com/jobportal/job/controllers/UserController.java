package com.jobportal.job.controllers;

import com.jobportal.job.dtos.UserDto;
import com.jobportal.job.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public String createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping
    public String updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/{uuid}")
    public String deleteUser(@PathVariable String uuid) {
        return userService.deleteUser(uuid);
    }

    @GetMapping("/{uuid}")
    public UserDto getUserByUuid(@PathVariable String uuid) {
        return userService.getUserByUuid(uuid);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
