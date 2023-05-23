package com.herookie.employee.dto;

import com.herookie.employee.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long userId;
    private String address;
    private String contactPhone;
    private String email;
    private String fullName;
    private String jobTitle;
    private Role role;

    // getters and setters
}
