package com.herookie.employee.services;

import java.util.List;

import com.herookie.employee.entities.LeaveRequest;
import com.herookie.employee.entities.LeaveStatus;
import com.herookie.employee.exceptions.EntityNotFoundException;
import com.herookie.employee.exceptions.ErrorProcessingException;
import com.herookie.employee.exceptions.UnsavedEntityException;

public interface ILeaveRequestService {
    // existing methods
    
    LeaveRequest createLeaveRequest(LeaveRequest leaveRequest) throws UnsavedEntityException;
    
    LeaveRequest updateLeaveRequestStatus(long leaveRequestId, LeaveStatus status, String deniedReason) throws EntityNotFoundException, UnsavedEntityException;

    List<LeaveRequest> findAll();

    LeaveRequest findById(long id) throws ErrorProcessingException, EntityNotFoundException;
}