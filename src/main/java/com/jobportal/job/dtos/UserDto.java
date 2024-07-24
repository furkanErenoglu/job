package com.jobportal.job.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String uuid;
    private String fullName;
    private String email;
    private String telephone;
    private String address;
    private String userRole;
    private String createdAt;
    private String updatedAt;
    private ProfileDto profile;
}
