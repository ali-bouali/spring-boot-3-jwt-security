package com.herookie.employee.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herookie.employee.entities.LeaveRequest;
import com.herookie.employee.entities.LeaveStatus;
import com.herookie.employee.entities.User;
import com.herookie.employee.exceptions.ErrorProcessingException;
import com.herookie.employee.exceptions.UnsavedEntityException;
import com.herookie.employee.repositories.LeaveRequestRepository;
import com.herookie.employee.repositories.UserRepository;
import com.herookie.employee.services.ILeaveRequestService;
import lombok.extern.slf4j.Slf4j;

import jakarta.persistence.EntityNotFoundException;

@Service
@Slf4j
public class LeaveRequestService implements ILeaveRequestService {

  @Autowired
  private LeaveRequestRepository leaveRequestRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<LeaveRequest> findAll() throws ErrorProcessingException {
    try {
      return this.leaveRequestRepository.findAll();
    } catch (final Exception e) {
      log.error("Leave findAll():{}", e.getMessage());
      throw new ErrorProcessingException(e.getMessage());
    }
  }

  @Override
  public LeaveRequest findById(final long id) throws ErrorProcessingException, EntityNotFoundException {
    try {
      return this.leaveRequestRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    } catch (final EntityNotFoundException e) {
      throw e;
    } catch (final Exception e) {
      log.error("Leave findById(\"{}\"):{}", id, e.getMessage());
      throw new ErrorProcessingException(e.getMessage());
    }
  }

  @Override
  public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest) throws UnsavedEntityException {
    try {
      User user = userRepository.findById(leaveRequest.getUser().getUserId()).orElseThrow(EntityNotFoundException::new);
      leaveRequest.setUser(user);
      return leaveRequestRepository.save(leaveRequest);
    } catch (Exception e) {
      throw new UnsavedEntityException(e.getMessage());
    }
  }

  @Override
  public LeaveRequest updateLeaveRequestStatus(long leaveRequestId, LeaveStatus status, String deniedReason)
      throws EntityNotFoundException, UnsavedEntityException {
    LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
        .orElseThrow(EntityNotFoundException::new);
    leaveRequest.setStatus(status);
    leaveRequest.setDeniedReason(deniedReason);
    return leaveRequestRepository.save(leaveRequest);
  }
}