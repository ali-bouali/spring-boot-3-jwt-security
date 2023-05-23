package com.herookie.employee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.herookie.employee.entities.LeaveStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestUpdateDto {
    private LeaveStatus status;
    @JsonProperty("denied_reason")
    private String deniedReason;
}