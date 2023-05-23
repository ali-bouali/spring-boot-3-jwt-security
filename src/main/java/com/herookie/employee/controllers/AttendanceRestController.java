package com.herookie.employee.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.herookie.employee.dto.AttendanceDTO;
import com.herookie.employee.dto.UserDTO;
import com.herookie.employee.entities.Attendance;
import com.herookie.employee.entities.User;
import com.herookie.employee.exceptions.EntityNotFoundException;
import com.herookie.employee.services.IAttendanceService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceRestController {

	@Autowired
	private IAttendanceService attendanceService;

	@GetMapping
	public ResponseEntity<List<AttendanceDTO>> getAllAttendances() {
		List<Attendance> attendances = attendanceService.findAll();
		List<AttendanceDTO> attendanceDTOs = convertToDTOList(attendances);
		return new ResponseEntity<>(attendanceDTOs, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AttendanceDTO> getAttendanceById(@PathVariable("id") Long id) throws EntityNotFoundException {
		Attendance attendance = attendanceService.findById(id);
		AttendanceDTO attendanceDTO = convertToDTO(attendance);
		return new ResponseEntity<>(attendanceDTO, HttpStatus.OK);
	}

	// Convert Attendance to AttendanceDTO
	private AttendanceDTO convertToDTO(Attendance attendance) {
		UserDTO userDTO = convertToDTO(attendance.getUser());
		return AttendanceDTO.builder()
				.attendanceId(attendance.getAttendanceId())
				.checkIn(attendance.getCheckIn())
				.checkOut(attendance.getCheckOut())
				.hasCheckIn(attendance.isHasCheckIn())
				.hasCheckOut(attendance.isHasCheckOut())
				.userDTO(userDTO)
				.build();
	}

	// Convert User to UserDTO
	private UserDTO convertToDTO(User user) {
		return UserDTO.builder()
				.userId(user.getUserId())
				.address(user.getAddress())
				.contactPhone(user.getContactPhone())
				.email(user.getEmail())
				.fullName(user.getFullName())
				.jobTitle(user.getJobTitle())
				.role(user.getRole())
				.build();
	}

	// Convert list of Attendance to list of AttendanceDTO
	private List<AttendanceDTO> convertToDTOList(List<Attendance> attendances) {
		return attendances.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	@PostMapping("/checkin/{userId}")
	public ResponseEntity<Attendance> checkIn(@PathVariable("userId") Long userId) throws EntityNotFoundException {
		Attendance attendance = attendanceService.checkIn(userId);
		return new ResponseEntity<>(attendance, HttpStatus.OK);
	}

	@PostMapping("/checkout/{userId}")
	public ResponseEntity<Attendance> checkOut(@PathVariable("userId") Long userId) throws EntityNotFoundException {
		Attendance attendance = attendanceService.checkOut(userId);
		return new ResponseEntity<>(attendance, HttpStatus.OK);
	}
}
