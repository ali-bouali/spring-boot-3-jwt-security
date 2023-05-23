package com.herookie.employee.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDTO {
    private long attendanceId;
    private Date checkIn;
    private Date checkOut;
    private boolean hasCheckIn;
    private boolean hasCheckOut;
    private UserDTO userDTO;

    // getters and setters
}
