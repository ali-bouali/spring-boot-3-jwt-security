package com.herookie.employee.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.herookie.employee.entities.LeaveStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestDTO {
    private Long id;
    private UserDTO user;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private String deniedReason;
    private LeaveStatus status;
    private LocalDateTime createdAt;
}
