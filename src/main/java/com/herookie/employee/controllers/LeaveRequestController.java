package com.herookie.employee.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.herookie.employee.dto.LeaveRequestDTO;
import com.herookie.employee.dto.LeaveRequestUpdateDto;
import com.herookie.employee.dto.UserDTO;
import com.herookie.employee.entities.LeaveRequest;
import com.herookie.employee.entities.User;
import com.herookie.employee.exceptions.EntityNotFoundException;
import com.herookie.employee.exceptions.UnsavedEntityException;
import com.herookie.employee.services.ILeaveRequestService;
import com.herookie.employee.services.IUserService;

@RestController
@RequestMapping("/leave-requests")
public class LeaveRequestController {

    @Autowired
    private ILeaveRequestService leaveRequestService;

    @Autowired
    IUserService userService;

    @GetMapping
    public ResponseEntity<List<LeaveRequestDTO>> getAllLeaveRequests() {
        List<LeaveRequest> leaveRequests = leaveRequestService.findAll();
        List<LeaveRequestDTO> leaveRequestDTOs = leaveRequests.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(leaveRequestDTOs, HttpStatus.OK);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequestDTO> getLeaveRequestById(@PathVariable("id") Long id)
            throws EntityNotFoundException {
        LeaveRequest leaveRequest = leaveRequestService.findById(id);
        LeaveRequestDTO leaveRequestDTO = convertToDTO(leaveRequest);
        return new ResponseEntity<>(leaveRequestDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LeaveRequestDTO> createLeaveRequest(@RequestBody LeaveRequest leaveRequest)
            throws UnsavedEntityException, EntityNotFoundException {
        User user = userService.findById(leaveRequest.getUser().getUserId());
        leaveRequest.setUser(user);

        LeaveRequest createdLeaveRequest = leaveRequestService.createLeaveRequest(leaveRequest);
        LeaveRequestDTO createdLeaveRequestDTO = convertToDTO(createdLeaveRequest);
        return new ResponseEntity<>(createdLeaveRequestDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveRequestDTO> updateLeaveRequestStatus(@PathVariable("id") Long id,
            @RequestBody LeaveRequestUpdateDto requestDto)
            throws EntityNotFoundException, UnsavedEntityException {
        LeaveRequest updatedLeaveRequest = leaveRequestService.updateLeaveRequestStatus(id, requestDto.getStatus(),
                requestDto.getDeniedReason());
        LeaveRequestDTO updatedLeaveRequestDTO = convertToDTO(updatedLeaveRequest);
        return new ResponseEntity<>(updatedLeaveRequestDTO, HttpStatus.OK);
    }

    // Helper method to convert LeaveRequest to LeaveRequestDTO
    private LeaveRequestDTO convertToDTO(LeaveRequest leaveRequest) {
        UserDTO userDTO = convertToDTO(leaveRequest.getUser());

        return LeaveRequestDTO.builder()
                .id(leaveRequest.getId())
                .user(userDTO)
                .startDate(leaveRequest.getStartDate())
                .endDate(leaveRequest.getEndDate())
                .reason(leaveRequest.getReason())
                .deniedReason(leaveRequest.getDeniedReason())
                .status(leaveRequest.getStatus())
                .createdAt(leaveRequest.getCreatedAt())
                .build();
    }

    // Helper method to convert User to UserDTO
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

}
